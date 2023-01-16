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

        Truck hop1 = Truck.builder()
                .regionGeoJson(String.valueOf(point1))
                .hopType("truck")
                .code("ABCD12")
                .description("next hop 1 of warehouse")
                .processingDelayMins(200)
                .locationName("Wien")
                .numberPlate("W-747200")
                .locationCoordinates(nextHopGC).build();

        WarehouseNextHops nextHop1 = WarehouseNextHops.builder()
                .hop(hop1)
                .traveltimeMins(400)
                .build();

        Truck hop2 = Truck.builder()
                .regionGeoJson(String.valueOf(point1))
                .hopType("truck")
                .code("EFGH34")
                .description("next hop 2 of warehouse")
                .processingDelayMins(56)
                .locationName("Tulln")
                .numberPlate("TU-20056")
                .locationCoordinates(nextHopGC).build();

        WarehouseNextHops nextHop2 = WarehouseNextHops.builder()
                .hop(hop2)
                .traveltimeMins(120)
                .build();

        List<WarehouseNextHops> nextHops = Arrays.asList(nextHop1, nextHop2);


        GeoCoordinate warehouseGC = GeoCoordinate.builder().lat(3493582346d).lon(345d).build();
        Warehouse warehouse = Warehouse.builder()
                //attributes from superclass hop:
                .hopType("warehouse")
                .code("ABCD12")
                .description("test description")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(warehouseGC)
                .level(2)
                .nextHops(nextHops)
                .build();

        WarehouseEntity entity = (WarehouseEntity) hopMapper.mapToSource(warehouse);

        log.warn(entity.toString());
    }

    @Test
    void testMapToSource() {
    }
}