package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import com.mapbox.geojson.Point;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class HopMapperTest {

    @Autowired
    private HopMapper hopMapper;

    @Test
    void mapToTarget() {
    }

    @Test
    void mapToSource() {
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

        WarehouseEntity entity = (WarehouseEntity) hopMapper.mapToSource(warehouse);

        log.warn(entity.toString());
    }

    @Test
    void testMapToSource() {
    }
}