package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ParcelApiControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void reportParcelDelivery() {
    }

    @Test
    void reportParcelHop() {
    }

    @Test
    void submitParcel() {
        /*ParcelEntity parcelEntity= bookMapper.mapToSource(book);
        log.info(entity.toString());*/
        submitParcel();
    }

    @Test
    void trackParcel() {
    }

    @Test
    void transitionParcel() {
    }

    @Test
    void getRequest() {
    }
}