package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {
    List<ParcelEntity> findByTrackingId(String trackingId);
}
