package at.fhtw.swen3.persistence.entity;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;


@SpringBootTest
class EntityValidatorTest {
   // @Autowired
    //private EntityValidator entityValidator;

    @Mock
    EntityValidator entityValidator;

    @Test
    void validate() {
        RecipientEntity recipient = RecipientEntity.builder()
                .name("test name")
                //.street("Hauptstraße 12/12/12")
                //.street("Landstraße 27a")
                .street("Arnold Fink Str. 13")
                .postalCode("asjfasjfbda")
                .city("Waidhofen an der Thaya")
                .country("Austria")
                .build();

        entityValidator.validate(recipient);
    }
}