package at.fhtw.swen3.services.dto;

import at.fhtw.swen3.services.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

@SpringBootTest
public class ParcelValidatorTest {
    @Autowired
    Validator validator;

    private @Mock
    Recipient recipient;
    private @Mock
    Recipient senderDto;

    @Test
    void validateParcelThrow(){
        Parcel parcel = Parcel.builder()
                .weight(0.0f)
                .recipient(recipient)
                .sender(senderDto)
                .build();

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            validator.validate(parcel);
        });
    }

    @Test
    void validateParcelNotThrow(){
        Parcel parcel = Parcel.builder()
                .weight(0.6f)
                .recipient(Recipient.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build())
                .sender(Recipient.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build())
                .build();

        Assertions.assertDoesNotThrow(() -> validator.validate(parcel));
    }
}
