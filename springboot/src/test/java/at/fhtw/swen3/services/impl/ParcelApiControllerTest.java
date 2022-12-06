package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.controller.rest.ParcelApiController;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class ParcelApiControllerTest {
    @Autowired
    ParcelApiController parcelApiController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void reportParcelDelivery() {
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