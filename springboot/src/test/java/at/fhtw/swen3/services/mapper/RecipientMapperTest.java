package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.RecipientDto;
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
        RecipientDto mappedRecipientDto = recipientMapper.mapToTarget(recipientEntity);

        assertEquals(mappedRecipientDto.getName(), recipientEntity.getName());
        assertEquals(mappedRecipientDto.getStreet(), recipientEntity.getStreet());
        assertEquals(mappedRecipientDto.getPostalCode(), recipientEntity.getPostalCode());
        assertEquals(mappedRecipientDto.getCity(), recipientEntity.getCity());
        assertEquals(mappedRecipientDto.getCountry(), recipientEntity.getCountry());
    }

    @Test
    void mapToSource() {
        RecipientDto recipientDto = RecipientDto.builder()
                .name("mapToSource test name")
                .street("Handelskai 300a")
                .postalCode("A-1020")
                .city("Wien")
                .country("Austria")
                .build();
        RecipientEntity mappedRecipientEntity = recipientMapper.mapToSource(recipientDto);

        assertEquals(mappedRecipientEntity.getName(), recipientDto.getName());
        assertEquals(mappedRecipientEntity.getStreet(), recipientDto.getStreet());
        assertEquals(mappedRecipientEntity.getPostalCode(), recipientDto.getPostalCode());
        assertEquals(mappedRecipientEntity.getCity(), recipientDto.getCity());
        assertEquals(mappedRecipientEntity.getCountry(), recipientDto.getCountry());
    }
}