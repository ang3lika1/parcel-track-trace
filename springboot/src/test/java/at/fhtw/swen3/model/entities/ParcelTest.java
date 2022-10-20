package at.fhtw.swen3.model.entities;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.persistence.repository.ParcelRepository;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ParcelTest {

    @Autowired
    private ParcelRepository parcelRepository;

    private @Mock HopArrivalEntity hopArrival;
    private final List<HopArrivalEntity> visitedHops = Arrays.asList(hopArrival, hopArrival, hopArrival);
    private final List<HopArrivalEntity> futuredHops = Arrays.asList(hopArrival, hopArrival, hopArrival);

    @Test
    public void testSaveNewParcel() {
        ParcelEntity parcel = ParcelEntity.builder()
                .weight(0.6f)
                .recipient(RecipientEntity.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build())
                .sender(RecipientEntity.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build())
                .trackingId("PYJRB4HZ6")
                .deliveryStatus(TrackingInformationDto.StateEnum.INTRANSPORT)
                .visitedHops(visitedHops)
                .futureHops(futuredHops)
                .build();

        parcelRepository.save(parcel);
        System.out.println(parcelRepository.count());
        parcelRepository.findAll().forEach(System.out::println);
        System.out.println("*******");
        parcelRepository.findByTrackingId("PYJRB4HZ6")
                .forEach(System.out::println);
    }
}