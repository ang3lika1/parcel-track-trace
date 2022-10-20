package at.fhtw.swen3.model.entities;

import at.fhtw.swen3.model.repositories.ParcelRepository;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class ParcelTest {

    @Autowired
    private ParcelRepository parcelRepository;

    @Test
    public void testSaveNewParcel() {
        Parcel parcel = Parcel.builder()
                .weight(0.6f)
                .recipient(Recipient.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build())
                .sender(Recipient.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build())
                .trackingId("PYJRB4HZ6")
                .deliveryStatus(TrackingInformationDto.StateEnum.INTRANSPORT)
                .visitedHops(new ArrayList<>())
                .futureHops(new ArrayList<>())
                .build();

        parcelRepository.save(parcel);
        System.out.println(parcelRepository.count());
        parcelRepository.findAll().forEach(System.out::println);
        System.out.println("*******");
        parcelRepository.findByTrackingId("PYJRB4HZ6")
                .forEach(System.out::println);
    }
}