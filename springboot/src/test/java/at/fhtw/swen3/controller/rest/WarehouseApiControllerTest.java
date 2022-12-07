package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WarehouseApiControllerTest {

    @Autowired
    WarehouseApiController warehouseApiController;

    @Test
    void getRequest() {
    }

    @Test
    void exportWarehouses() {
    }

    @Test
    void getWarehouse() {
        ResponseEntity<Hop> warehouse = warehouseApiController.getWarehouse("abdsa");
        assertEquals(warehouse, new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Test
    void importWarehouses() {
    }
}