package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
@Slf4j
class WarehouseNextHopsRepositoryTest {
    @Autowired
    private WarehouseNextHopsRepository warehouseNextHopRepository;

    private @Mock WarehouseNextHopsEntity warehouseNextHop;
    private final List<WarehouseNextHopsEntity> nextHops = Arrays.asList(warehouseNextHop, warehouseNextHop, warehouseNextHop);

    @Autowired
    private WarehouseRepository warehouseRepository;
    private @Mock WarehouseEntity mockedWarehouse;
    private @Mock GeoCoordinateEntity mockedGeoCoordinate;
    //private @Mock HopEntity mockedHop;


   /* @Test
    public void should_find_all_warehouse_next_hops() {

        WarehouseEntity warehouse = WarehouseEntity.builder().level(2).nextHops(nextHops).build();
        WarehouseEntity createdWarehouse = warehouseRepository.save(warehouse);

        WarehouseNextHopsEntity warehouseNextHop1 = WarehouseNextHopsEntity.builder().traveltimeMins(320).hop(mockedHop).warehouse(createdWarehouse).build();
        warehouseNextHopRepository.save(warehouseNextHop1);

        WarehouseNextHopsEntity warehouseNextHop2 = WarehouseNextHopsEntity.builder().traveltimeMins(60).hop(mockedHop).warehouse(createdWarehouse).build();
        warehouseNextHopRepository.save(warehouseNextHop2);

        WarehouseNextHopsEntity warehouseNextHop3 = WarehouseNextHopsEntity.builder().traveltimeMins(193).hop(mockedHop).warehouse(createdWarehouse).build();
        warehouseNextHopRepository.save(warehouseNextHop3);


        Iterable<WarehouseNextHopsEntity> nextHops = warehouseNextHopRepository.findAll();

        assertThat(nextHops).hasSize(3).contains(warehouseNextHop1, warehouseNextHop2, warehouseNextHop3);
    }*/

    /*@Test
    public void should_update_warehouse_next_hop_by_id() {
        //GeoCoordinateEntity geoCoordinate =GeoCoordinateEntity.builder().lat(3493582346d).lon(345d).build();
        WarehouseEntity mockedHop = WarehouseEntity.builder()
                //attributes from superclass hop:
                .hopType("warehouse")
                .code("ABCD12")
                .description("test description")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(mockedGeoCoordinate)
                .level(2).nextHops(nextHops).build();   //from warehouse itself

        WarehouseNextHopsEntity warehouseNextHop1 = WarehouseNextHopsEntity.builder().traveltimeMins(320).hop(mockedHop).build();
        warehouseNextHopRepository.save(warehouseNextHop1);

        WarehouseNextHopsEntity warehouseNextHop2 = WarehouseNextHopsEntity.builder().traveltimeMins(60).hop(mockedHop).build();
        warehouseNextHopRepository.save(warehouseNextHop2);

        WarehouseNextHopsEntity updatedWarehouseNextHop = WarehouseNextHopsEntity.builder().traveltimeMins(77).hop(mockedHop).build();
        warehouseNextHopRepository.save(updatedWarehouseNextHop);


        WarehouseNextHopsEntity warehouseNextHop = warehouseNextHopRepository.findById(warehouseNextHop2.getId()).get();
        warehouseNextHop.setTraveltimeMins(updatedWarehouseNextHop.getTraveltimeMins());;
        warehouseNextHopRepository.save(warehouseNextHop);

        WarehouseNextHopsEntity checkWarehouseNextHop = warehouseNextHopRepository.findById(warehouseNextHop2.getId()).get();

        assertThat(checkWarehouseNextHop.getId()).isEqualTo(warehouseNextHop2.getId());
        assertThat(checkWarehouseNextHop.getTraveltimeMins()).isEqualTo(updatedWarehouseNextHop.getTraveltimeMins());
    }*/

}