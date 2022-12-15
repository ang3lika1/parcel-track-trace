package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.controller.rest.ParcelApiController;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
@Slf4j
class ParcelApiControllerTest {
    @Autowired
    ParcelApiController parcelApiController;
    @Autowired
    ParcelRepository parcelRepository;

    ParcelEntity parcelEntity;

    @BeforeEach
    void setUp() {
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

        parcelRepository.save(parcelEntity);

    }

    @Test
    void reportParcelDelivery() {
        ResponseEntity<Void> delivery = parcelApiController.reportParcelDelivery(parcelEntity.getTrackingId());
        assertEquals(delivery, new ResponseEntity<>( HttpStatus.OK));
        try {
            assertEquals(parcelRepository.findByTrackingId(parcelEntity.getTrackingId()).getDeliveryStatus(), TrackingInformation.StateEnum.DELIVERED);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void reportParcelHop() {
    }

    @Test
    void submitParcel() {
       Parcel parcel = Parcel.builder()
                .weight(0.6f)
                .recipient(Recipient.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build())
                .sender(Recipient.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build())
                .build();
        parcelApiController.submitParcel(parcel);
    }

    @Test
    void trackParcel() {
    }

    @Test
    void transitionParcel() {
    }

    @Test
    void getRequest() {
    }
}