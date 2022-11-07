package at.fhtw.swen3.services.dto;

import at.fhtw.swen3.services.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

@SpringBootTest
public class RecipientDtoTest {
    @Autowired
    Validator validator;

    @Test
    void validateRecipientThrow() {
        RecipientDto recipientDto = RecipientDto.builder()
                .name("test name")
                .street("Arnold Fink Str. 13")
                .postalCode("A-3667830")
                .city("Waidhofen an der Thaya")
                .country("Austria")
                .build();

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            validator.validate(recipientDto);
        });
    }

    @Test
    void validateRecipientNotThrow() {
        RecipientDto recipientDto = RecipientDto.builder()
                .name("test name")
                .street("Landstraße 27a")
                .postalCode("A-3500")
                .city("Krems an der Donau")
                .country("Austria")
                .build();

        Assertions.assertDoesNotThrow(() -> validator.validate(recipientDto));
    }
}
