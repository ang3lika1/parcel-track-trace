package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;


public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {
    ParcelEntity findByTrackingId(String trackingId) throws SQLException;
}
