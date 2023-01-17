package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.services.dto.*;
import com.mapbox.geojson.Point;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
@Transactional
class WarehouseApiControllerTest {

    @Autowired
    WarehouseApiController warehouseApiController;

    @Autowired
    WarehouseNextHopsRepository warehouseNextHopsRepository;


    @Test
    void getRequest() {
    }

    @Test
    void exportWarehouses() {
        ResponseEntity<Warehouse> warehouseHierarchy = warehouseApiController.exportWarehouses();
        log.info(String.valueOf(warehouseHierarchy));
    }

    @Test
    void getWarehouse() {
        ResponseEntity<Hop> warehouse = warehouseApiController.getWarehouse("abdsa");
        assertEquals(warehouse, new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }


    @Test
    void importWarehouses() {
        GeoCoordinate nextHopGC = GeoCoordinate.builder().lat(7582346d).lon(285d).build();
        Point point1 = Point.fromLngLat(nextHopGC.getLon(), nextHopGC.getLat());

        Truck hop1 = new Truck(
                "truck",
                "ABCD12",
                "next hop 1 of warehouse",
                200,
                "Wien",
                nextHopGC,
                String.valueOf(point1),
                "W-747200");

        WarehouseNextHops nextHop1 = WarehouseNextHops.builder()
                .hop(hop1)
                .traveltimeMins(400)
                .build();

        Truck hop2 = new Truck(
                "truck",
                "EFGH34",
                "next hop 2 of warehouse",
                56,
                "Tulln",
                nextHopGC,
                String.valueOf(point1),
                "TU-20056");

        WarehouseNextHops nextHop2 = WarehouseNextHops.builder()
                .hop(hop2)
                .traveltimeMins(120)
                .build();

        List<WarehouseNextHops> nextHops = Arrays.asList(nextHop1, nextHop2);

        GeoCoordinate warehouseGC = GeoCoordinate.builder().lat(3493582346d).lon(345d).build();

        Warehouse warehouse = new Warehouse(
                "warehouse",
                "ABCD12",
                "test description",
                55,
                "Wien",
                warehouseGC,
                2,
                nextHops);

        ResponseEntity<Void> importedWarehouse = warehouseApiController.importWarehouses(warehouse);
        assertEquals(importedWarehouse, new ResponseEntity<>( HttpStatus.OK));
    }
}