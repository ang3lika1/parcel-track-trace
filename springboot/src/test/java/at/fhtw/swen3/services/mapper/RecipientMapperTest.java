package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipientMapperTest {
    RecipientMapper recipientMapper = new RecipientMapper();

    @Test
    void mapToTarget() {
        RecipientEntity recipientEntity = RecipientEntity.builder()
                .name("mapToTarget test name")
                .street("Hauptstraße 12/12/12")
                .postalCode("A-3500")
                .city("Krems an der Donau")
                .country("Austria")
                .build();
        Recipient mappedRecipient = recipientMapper.mapToTarget(recipientEntity);

        assertEquals(mappedRecipient.getName(), recipientEntity.getName());
        assertEquals(mappedRecipient.getStreet(), recipientEntity.getStreet());
        assertEquals(mappedRecipient.getPostalCode(), recipientEntity.getPostalCode());
        assertEquals(mappedRecipient.getCity(), recipientEntity.getCity());
        assertEquals(mappedRecipient.getCountry(), recipientEntity.getCountry());
    }

    @Test
    void mapToSource() {
        Recipient recipient = Recipient.builder()
                .name("mapToSource test name")
                .street("Handelskai 300a")
                .postalCode("A-1020")
                .city("Wien")
                .country("Austria")
                .build();
        RecipientEntity mappedRecipientEntity = recipientMapper.mapToSource(recipient);

        assertEquals(mappedRecipientEntity.getName(), recipient.getName());
        assertEquals(mappedRecipientEntity.getStreet(), recipient.getStreet());
        assertEquals(mappedRecipientEntity.getPostalCode(), recipient.getPostalCode());
        assertEquals(mappedRecipientEntity.getCity(), recipient.getCity());
        assertEquals(mappedRecipientEntity.getCountry(), recipient.getCountry());
    }
}