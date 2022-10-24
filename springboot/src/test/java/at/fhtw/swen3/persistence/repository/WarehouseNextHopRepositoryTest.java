package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entity.HopEntity;
import at.fhtw.swen3.persistence.entity.WarehouseEntity;
import at.fhtw.swen3.persistence.entity.WarehouseNextHopEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class WarehouseNextHopRepositoryTest {
    @Autowired
    private WarehouseNextHopRepository warehouseNextHopRepository;
    private @Mock WarehouseEntity mockedWarehouse;
    private @Mock HopEntity mockedHop;

    @Test
    public void should_find_all_warehouse_next_hops() {
        WarehouseNextHopEntity warehouseNextHop1 = WarehouseNextHopEntity.builder().traveltimeMins(320).hop(mockedHop).build();
        warehouseNextHopRepository.save(warehouseNextHop1);

        WarehouseNextHopEntity warehouseNextHop2 = WarehouseNextHopEntity.builder().traveltimeMins(60).hop(mockedHop).build();
        warehouseNextHopRepository.save(warehouseNextHop2);

        WarehouseNextHopEntity warehouseNextHop3 = WarehouseNextHopEntity.builder().traveltimeMins(193).hop(mockedHop).build();
        warehouseNextHopRepository.save(warehouseNextHop3);


        Iterable<WarehouseNextHopEntity> nextHops = warehouseNextHopRepository.findAll();

        assertThat(nextHops).hasSize(3).contains(warehouseNextHop1, warehouseNextHop2, warehouseNextHop3);
    }

    @Test
    public void should_update_warehouse_next_hop_by_id() {
        WarehouseNextHopEntity warehouseNextHop1 = WarehouseNextHopEntity.builder().traveltimeMins(320).hop(mockedHop).build();
        warehouseNextHopRepository.save(warehouseNextHop1);

        WarehouseNextHopEntity warehouseNextHop2 = WarehouseNextHopEntity.builder().traveltimeMins(60).hop(mockedHop).build();
        warehouseNextHopRepository.save(warehouseNextHop2);

        WarehouseNextHopEntity updatedWarehouseNextHop = WarehouseNextHopEntity.builder().traveltimeMins(77).hop(mockedHop).build();
        warehouseNextHopRepository.save(updatedWarehouseNextHop);


        WarehouseNextHopEntity warehouseNextHop = warehouseNextHopRepository.findById(warehouseNextHop2.getId()).get();
        warehouseNextHop.setTraveltimeMins(updatedWarehouseNextHop.getTraveltimeMins());;
        warehouseNextHopRepository.save(warehouseNextHop);

        WarehouseNextHopEntity checkWarehouseNextHop = warehouseNextHopRepository.findById(warehouseNextHop2.getId()).get();

        assertThat(checkWarehouseNextHop.getId()).isEqualTo(warehouseNextHop2.getId());
        assertThat(checkWarehouseNextHop.getTraveltimeMins()).isEqualTo(updatedWarehouseNextHop.getTraveltimeMins());
    }

}