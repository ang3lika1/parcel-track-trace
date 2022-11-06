package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.persistence.repository.ParcelRepository;
import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.dto.RecipientDto;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
//@Transactional
class ParcelServiceImplTest {
    private @Mock
    RecipientDto recipientDto;
    private @Mock
    RecipientDto senderDto;

    @Autowired
    private ParcelService parcelService;

    @InjectMocks
    private ParcelServiceImpl parcelServiceImpl;

    @Mock
    private ParcelRepository parcelRepository;
    @Mock
    private Validator validator;
    @Mock
    private ParcelMapper parcelMapper;


    private ParcelEntity parcel;
    private ParcelDto parcelDto;

    @BeforeEach
    public void setup(){
        RecipientEntity recipient = RecipientEntity.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build();
        RecipientEntity sender = RecipientEntity.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build();

        final List<HopArrivalEntity> fakeHops = Arrays.asList(null, null, null);

        parcel = ParcelEntity.builder()
                .weight(0.6f)
                .recipient(recipient)
                .sender(sender)
                .trackingId("PYJRB4HZ6")
                .deliveryStatus(TrackingInformationDto.StateEnum.INTRANSPORT)
                .visitedHops(fakeHops)
                .futureHops(fakeHops)
                .build();

        parcelDto = ParcelDto.builder()
                .weight(0.6f)
                .recipient(RecipientDto.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build())
                .sender(RecipientDto.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build())
                .build();
    }

    @Test
    void saveNewParcel() {
        ParcelDto savedParcel = parcelService.saveNewParcel(parcelDto);
        parcelServiceImpl.saveNewParcel(parcelDto);
        assertThat(savedParcel).isEqualTo(parcelDto);
    }

    @Test
    void trackParcel() {
        parcelRepository.save(parcel);

        TrackingInformationDto trackedParcel = parcelService.trackParcel(parcel.getTrackingId());

        assertThat(trackedParcel).isEqualTo(parcelMapper.toTrackingInfoDto(parcel));
    }


    /*@Test
    void should_save_one_parcel() {
        // Arrange
        when(parcelRepository.save(any(ParcelEntity.class))).thenReturn(parcel);

        // Act
        parcelServiceImpl.saveNewParcel(parcelDto);

        // Assert
        //assertThat(actual).isEqualTo(parcelDto);
        verify(parcelRepository, times(1)).save(any(ParcelEntity.class));
        verifyNoMoreInteractions(parcelRepository);
    }*/
}