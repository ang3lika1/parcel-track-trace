package at.fhtw.swen3.persistence.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;


@SpringBootTest
class EntityValidatorTest {

    @Autowired
    EntityValidator entityValidator;

    @Test
    void validateThrow() {
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
    void validateNotThrow() {
        RecipientEntity recipient = RecipientEntity.builder()
                .name("test name")
                //.street("Hauptstraße 12/12/12")
                .street("Landstraße 27a")
                .postalCode("A-3830")
                .city("Waidhofen an der Thaya")
                .country("Austria")
                .build();

        Assertions.assertDoesNotThrow(() -> entityValidator.validate(recipient));
    }
}