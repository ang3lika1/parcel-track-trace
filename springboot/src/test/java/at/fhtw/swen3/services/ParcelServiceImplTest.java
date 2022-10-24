package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.ParcelDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParcelServiceImplTest {
    private @Mock
    ParcelDto parcelDto;

    @Autowired
    ParcelService parcelService;

    @Test
    void saveNewParcel() {

    }
}