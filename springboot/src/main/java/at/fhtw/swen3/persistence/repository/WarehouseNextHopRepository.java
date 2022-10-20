package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.WarehouseEntity;
import at.fhtw.swen3.persistence.entity.WarehouseNextHopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseNextHopRepository extends JpaRepository<WarehouseNextHopEntity, Long> {
}
