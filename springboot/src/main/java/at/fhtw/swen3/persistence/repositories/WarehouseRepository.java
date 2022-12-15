package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
    WarehouseEntity findByCode(String code) throws SQLException;
    WarehouseEntity findFirstByIdIsNotNullOrderByNextHops();
}
