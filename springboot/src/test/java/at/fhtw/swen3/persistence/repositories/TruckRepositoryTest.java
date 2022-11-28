package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TruckRepositoryTest {
    @Autowired
    private TruckRepository truckRepository;

    @Test
    public void should_find_no_trucks_if_repository_is_empty() {
        Iterable<TruckEntity> hops = truckRepository.findAll();

        assertThat(hops).isEmpty();
    }

    @Test
    public void should_store_a_truck() {
        GeoCoordinateEntity geoCoordinate =GeoCoordinateEntity.builder().lat(3493582346d).lon(345d).build();
        TruckEntity truck = TruckEntity.builder()
                //attributes from superclass hop:
                .hopType("truck")
                .code("EFGH34")
                .description("test description")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate)
                .regionGeoJson("GeoJsonString").numberPlate("W-747200").build();    //from truckentity itself

        TruckEntity createdTruck = truckRepository.save(truck);
        assertThat(createdTruck).hasFieldOrPropertyWithValue("regionGeoJson", "GeoJsonString");
        assertThat(createdTruck).hasFieldOrPropertyWithValue("numberPlate", "W-747200");
        assertEquals(truckRepository.count(),1);
    }

    @Test
    public void should_find_truck_by_id() {
        TruckEntity truck1 = TruckEntity.builder().regionGeoJson("GeoJsonString 1").numberPlate("W-747200").build();
        TruckEntity truck2 = TruckEntity.builder().regionGeoJson("GeoJsonString 2").numberPlate("W-747201").build();
        truckRepository.save(truck1);
        truckRepository.save(truck2);

        TruckEntity foundTruck = truckRepository.findById(truck2.getId()).get();
        assertThat(foundTruck).isEqualTo(truck2);
    }

}