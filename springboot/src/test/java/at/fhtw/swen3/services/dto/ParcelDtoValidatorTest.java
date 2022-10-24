package at.fhtw.swen3.services.dto;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ParcelDtoValidatorTest {
    @Autowired
    Validator validator;

    private @Mock
    RecipientDto recipientDto;
    private @Mock
    RecipientDto senderDto;

    @Test
    void validateParcelThrow(){
        ParcelDto parcelDto = ParcelDto.builder()
                .weight(0.0f)
                .recipient(recipientDto)
                .sender(senderDto)
                .build();

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            validator.validate(parcelDto);
        });
    }

    @Test
    void validateParcelNotThrow(){
        ParcelDto parcelDto = ParcelDto.builder()
                .weight(0.6f)
                .recipient(RecipientDto.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build())
                .sender(RecipientDto.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build())
                .build();

        Assertions.assertDoesNotThrow(() -> validator.validate(parcelDto));
    }
}
