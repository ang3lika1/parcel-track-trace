package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.TrackingInformationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
class EntityValidatorTest {

    @Autowired
    EntityValidator entityValidator;

    //for Tests: "validateParcelNotThrow" and "validateParcelNotThrow()"
    private @Mock HopArrivalEntity hopArrival;
    private final List<HopArrivalEntity> visitedHops = Arrays.asList(hopArrival, hopArrival, hopArrival);
    private final List<HopArrivalEntity> futuredHops = Arrays.asList(hopArrival, hopArrival, hopArrival);

    @Test
    void validateRecipientThrow() {
        RecipientEntity recipient = RecipientEntity.builder()
                .name("test name")
                .street("Arnold Fink Str. 13")
                .postalCode("A-3667830")
                .city("Waidhofen an der Thaya")
                .country("Austria")
                .build();

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            entityValidator.validate(recipient);
        });
    }

    @Test
    void validateRecipientNotThrow() {
        RecipientEntity recipient = RecipientEntity.builder()
                .name("test name")
                .street("Landstraße 27a")
                .postalCode("A-3500")
                .city("Krems an der Donau")
                .country("Austria")
                .build();

        Assertions.assertDoesNotThrow(() -> entityValidator.validate(recipient));
    }

    @Test
    void validateParcelThrow(){
        ParcelEntity parcel = ParcelEntity.builder()
                .weight(0.6f)
                .recipient(RecipientEntity.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build())
                .sender(RecipientEntity.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build())
                .trackingId("PYJRB4HZ6")
                .deliveryStatus(TrackingInformationDto.StateEnum.INTRANSPORT)
                .futureHops(futuredHops)
                .build();

        //Violation because visitedHops Null but has NotNull Annotation
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            entityValidator.validate(parcel);
        });
    }

    @Test
    void validateParcelNotThrow(){
        ParcelEntity parcel = ParcelEntity.builder()
                .weight(0.6f)
                .recipient(RecipientEntity.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build())
                .sender(RecipientEntity.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build())
                .trackingId("PYJRB4HZ6")
                .deliveryStatus(TrackingInformationDto.StateEnum.INTRANSPORT)
                .visitedHops(visitedHops)
                .futureHops(futuredHops)
                .build();
        Assertions.assertDoesNotThrow(() -> entityValidator.validate(parcel));
    }
}