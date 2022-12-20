package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
class ParcelServiceImplTest {
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private ParcelMapper parcelMapper;

    private ParcelEntity parcelEntity;
    private Parcel parcel;

    @BeforeEach
    public void setup(){
        RecipientEntity recipient = RecipientEntity.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build();
        RecipientEntity sender = RecipientEntity.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build();

        final List<HopArrivalEntity> fakeHops = Arrays.asList(null, null, null);

        parcelEntity = ParcelEntity.builder()
                .weight(0.6f)
                .recipient(recipient)
                .sender(sender)
                .trackingId("PYJRB4HZ6")
                .deliveryStatus(TrackingInformation.StateEnum.INTRANSPORT)
                .visitedHops(fakeHops)
                .futureHops(fakeHops)
                .build();

        parcel = Parcel.builder()
                .weight(0.6f)
                .recipient(Recipient.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build())
                .sender(Recipient.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build())
                .build();
    }

    @Test
    void saveNewParcel()  {
        NewParcelInfo savedParcelInfo = parcelService.saveNewParcel(parcel);
        ParcelEntity savedParcelEntity = null;
        try {
            savedParcelEntity = parcelRepository.findByTrackingId(savedParcelInfo.getTrackingId());
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        assert savedParcelEntity != null;
        assertThat(savedParcelInfo.getTrackingId()).isEqualTo(savedParcelEntity.getTrackingId());
        assertThat(parcelMapper.toParcelDto(savedParcelEntity)).isEqualTo(parcel);
    }

    @Test
    void trackParcel() {
        parcelRepository.save(parcelEntity);

        TrackingInformation trackedParcel = null;
        try {
            trackedParcel = parcelService.trackParcel(parcelEntity.getTrackingId());
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }

        assertThat(trackedParcel).isEqualTo(parcelMapper.toTrackingInfoDto(parcelEntity));
    }

    @Test
    void saveExistingParcelTrackingIdExists() {
        parcelRepository.save(parcelEntity);
        try {
            ParcelEntity parcelCheck = parcelRepository.findByTrackingId(parcelEntity.getTrackingId());
            log.info(parcelCheck.getTrackingId());
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        ResponseEntity<NewParcelInfo> response = null;
        try {
            response = parcelService.saveExistingParcel(parcelEntity.getTrackingId(), parcel);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        assertThat(response).isEqualTo(new ResponseEntity<>(null, HttpStatus.CONFLICT));
    }

}