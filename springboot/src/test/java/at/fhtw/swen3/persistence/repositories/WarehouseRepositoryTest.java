package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class WarehouseRepositoryTest {
    @Autowired
    private WarehouseRepository warehouseRepository;
    private @Mock WarehouseNextHopsEntity warehouseNextHop;
    private final List<WarehouseNextHopsEntity> nextHops = Arrays.asList(warehouseNextHop, warehouseNextHop, warehouseNextHop);

    @Test
    public void should_find_no_warehouse_if_repository_is_empty() {
        Iterable<WarehouseEntity> warehouses = warehouseRepository.findAll();

        assertThat(warehouses).isEmpty();
    }

    @Test
    public void should_store_a_warehouse() {
        //WarehouseEntity warehouse = WarehouseEntity.builder().level(2).nextHops(nextHops).build();
        GeoCoordinateEntity geoCoordinate =GeoCoordinateEntity.builder().lat(3493582346d).lon(345d).build();
        WarehouseEntity warehouse = WarehouseEntity.builder()
                //attributes from superclass hop:
                .hopType("warehouse")
                .code("ABCD12")
                .description("test description")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate)
                .level(2).nextHops(nextHops).build();   //from warehouse itself

        WarehouseEntity createdWarehouse = warehouseRepository.save(warehouse);
        assertThat(createdWarehouse).hasFieldOrPropertyWithValue("level", 2);
        assertEquals(warehouseRepository.count(),1);
    }

    @Test
    public void should_delete_warehouse_by_id() {
        GeoCoordinateEntity geoCoordinate =GeoCoordinateEntity.builder().lat(3493582346d).lon(345d).build();
        //WarehouseEntity warehouse1 = WarehouseEntity.builder().level(5).nextHops(nextHops).build();
        //WarehouseEntity warehouse2 = WarehouseEntity.builder().level(1).nextHops(nextHops).build();

        WarehouseEntity warehouse1 = WarehouseEntity.builder()
                //attributes from superclass hop:
                .hopType("warehouse")
                .code("ABCD12")
                .description("test description")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate)
                .level(5).nextHops(nextHops).build();   //from warehouse itself

        WarehouseEntity warehouse2 = WarehouseEntity.builder()
                //attributes from superclass hop:
                .hopType("warehouse")
                .code("ABCD12")
                .description("test description")
                .processingDelayMins(300)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate)
                .level(1).nextHops(nextHops).build();   //from warehouse itself

        warehouseRepository.save(warehouse1);
        warehouseRepository.save(warehouse2);

        warehouseRepository.deleteById(warehouse1.getId());

        Iterable<WarehouseEntity> hops = warehouseRepository.findAll();

        assertThat(hops).hasSize(1).contains(warehouse2);
    }
}