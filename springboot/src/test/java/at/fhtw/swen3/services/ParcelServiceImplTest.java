package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.dto.RecipientDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ParcelServiceImplTest {
    private @Mock
    RecipientDto recipientDto;
    private @Mock
    RecipientDto senderDto;

    @Autowired
    private ParcelService parcelService;

    @Test
    void saveNewParcel() {
        ParcelDto parcelDto = ParcelDto.builder()
                .weight(0.6f)
                .recipient(RecipientDto.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build())
                .sender(RecipientDto.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build())
                .build();

        parcelService.saveNewParcel(parcelDto);
    }
}