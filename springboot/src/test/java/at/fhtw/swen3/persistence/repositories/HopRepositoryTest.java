package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
class HopRepositoryTest {
    @Autowired
    private HopRepository hopRepository;

    @Autowired
    private TestEntityManager entityManager; //intellij error?


    @Test
    public void should_find_no_hops_if_repository_is_empty() {
        Iterable<HopEntity> hops = hopRepository.findAll();

        assertThat(hops).isEmpty();
    }

    @Test
    public void should_store_a_hop() {

        GeoCoordinateEntity geoCoordinate =GeoCoordinateEntity.builder().lat(3493582346d).lon(345d).build();
        HopEntity hop = HopEntity.builder()
                .hopType("testHopType")
                .code("ABCD12")
                .description("test description")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate).build();


        HopEntity createdHop = hopRepository.save(hop);
        assertThat(createdHop).hasFieldOrPropertyWithValue("hopType", "testHopType");
        assertThat(createdHop).hasFieldOrPropertyWithValue("description", "test description");
        assertThat(createdHop).hasFieldOrPropertyWithValue("processingDelayMins", 55);
    }

    @Test
    public void should_find_all_hops() {
        GeoCoordinateEntity geoCoordinate =GeoCoordinateEntity.builder().lat(3493582346d).lon(345d).build();
        HopEntity hop1 = HopEntity.builder()
                .hopType("testHopType 1")
                .code("ABCD12")
                .description("test description 1")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate).build();
        entityManager.persist(hop1);

        HopEntity hop2 = HopEntity.builder()
                .hopType("testHopType 2")
                .code("ABCD12")
                .description("test description 2")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate).build();
        entityManager.persist(hop2);


        Iterable<HopEntity> tutorials = hopRepository.findAll();

        assertThat(tutorials).hasSize(2).contains(hop1, hop2);
    }

    @Test
    public void should_find_hop_by_id() {
        GeoCoordinateEntity geoCoordinate =GeoCoordinateEntity.builder().lat(3493582346d).lon(345d).build();
        HopEntity hop1 = HopEntity.builder()
                .hopType("testHopType 1")
                .code("ABCD12")
                .description("test description 1")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate).build();
        entityManager.persist(hop1);

        HopEntity hop2 = HopEntity.builder()
                .hopType("testHopType 2")
                .code("ABCD12")
                .description("test description 2")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate).build();
        entityManager.persist(hop2);
        HopEntity foundHop = hopRepository.findById(hop2.getId()).get();

        assertThat(foundHop).isEqualTo(hop2);
    }

    @Test
    public void should_update_hop_by_id() {
        GeoCoordinateEntity geoCoordinate =GeoCoordinateEntity.builder().lat(3493582346d).lon(345d).build();
        HopEntity hop1 = HopEntity.builder()
                .hopType("testHopType 1")
                .code("ABCD12")
                .description("test description 1")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate).build();
        entityManager.persist(hop1);

        HopEntity hop2 = HopEntity.builder()
                .hopType("testHopType 2")
                .code("ABCD12")
                .description("test description 2")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate).build();
        entityManager.persist(hop2);

        HopEntity updatedHop = HopEntity.builder()
                .hopType("updated testHopType")
                .code("ABCD12")
                .description("updated test description")
                .processingDelayMins(55)
                .locationName("Krems")
                .locationCoordinates(geoCoordinate).build();

        HopEntity hop = hopRepository.findById(hop2.getId()).get();
        hop.setHopType(updatedHop.getHopType());
        hop.setDescription(updatedHop.getDescription());
        hop.setLocationName(updatedHop.getLocationName());
        hopRepository.save(hop);

        HopEntity checkTut = hopRepository.findById(hop2.getId()).get();

        assertThat(checkTut.getId()).isEqualTo(hop2.getId());
        assertThat(checkTut.getHopType()).isEqualTo(updatedHop.getHopType());
        assertThat(checkTut.getDescription()).isEqualTo(updatedHop.getDescription());
        assertThat(checkTut.getLocationName()).isEqualTo(updatedHop.getLocationName());
    }

    @Test
    public void should_delete_tutorial_by_id() {
        GeoCoordinateEntity geoCoordinate =GeoCoordinateEntity.builder().lat(3493582346d).lon(345d).build();
        HopEntity hop1 = HopEntity.builder()
                .hopType("testHopType 1")
                .code("ABCD12")
                .description("test description 1")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate).build();
        entityManager.persist(hop1);

        HopEntity hop2 = HopEntity.builder()
                .hopType("testHopType 2")
                .code("ABCD12")
                .description("test description 2")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate).build();
        entityManager.persist(hop2);

        hopRepository.deleteById(hop2.getId());

        Iterable<HopEntity> hops = hopRepository.findAll();

        assertThat(hops).hasSize(1).contains(hop1);
    }

    @Test
    public void should_delete_all_tutorials() {
        GeoCoordinateEntity geoCoordinate =GeoCoordinateEntity.builder().lat(3493582346d).lon(345d).build();
        HopEntity hop1 = HopEntity.builder()
                .hopType("testHopType 1")
                .code("ABCD12")
                .description("test description 1")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate).build();
        entityManager.persist(hop1);

        HopEntity hop2 = HopEntity.builder()
                .hopType("testHopType 2")
                .code("ABCD12")
                .description("test description 2")
                .processingDelayMins(55)
                .locationName("Wien")
                .locationCoordinates(geoCoordinate).build();
        entityManager.persist(hop2);

        hopRepository.deleteAll();

        //assertThat(hopRepository.findAll()).isEmpty();
    }

}