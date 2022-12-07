package at.fhtw.swen3.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
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
        warehouseApiController.getWarehouse("abdsa");
    }

    @Test
    void importWarehouses() {
    }
}