package at.fhtw.swen3.model.entities;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.persistence.repository.ParcelRepository;
import at.fhtw.swen3.persistence.repository.RecipientRepository;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ParcelTest {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    private @Mock HopArrivalEntity hopArrival;
    private final List<HopArrivalEntity> visitedHops = Arrays.asList(hopArrival, hopArrival, hopArrival);
    private final List<HopArrivalEntity> futuredHops = Arrays.asList(hopArrival, hopArrival, hopArrival);

    @Test
    public void testSaveNewRecipient() {
        RecipientEntity recipient = RecipientEntity.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build();
        RecipientEntity sender = RecipientEntity.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build();
        recipientRepository.save(recipient);
        recipientRepository.save(sender);

        System.out.println(recipientRepository.count());
        recipientRepository.findAll().forEach(System.out::println);
        System.out.println("*******");
        recipientRepository.findByName("sendername")
                .forEach(System.out::println);
    }

    @Test
    @Transactional
    public void testRecipientToParcel() {
        RecipientEntity recipient = RecipientEntity.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build();
        RecipientEntity sender = RecipientEntity.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build();
        recipientRepository.save(recipient);
        recipientRepository.save(sender);


        ParcelEntity parcel = ParcelEntity.builder()
                .weight(0.6f)
                .recipient(recipient)
                .sender(sender)
                .trackingId("PYJRB4HZ6")
                .deliveryStatus(TrackingInformationDto.StateEnum.INTRANSPORT)
                .visitedHops(visitedHops)
                .futureHops(futuredHops)
                .build();
        parcelRepository.save(parcel);

        parcel.setSender(sender);
        parcel.setSender(recipient);

        parcelRepository.findAll().forEach(System.out::println);
    }
}