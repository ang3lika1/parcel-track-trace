package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.HopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HopRepository extends JpaRepository<HopEntity, Long> {
}
