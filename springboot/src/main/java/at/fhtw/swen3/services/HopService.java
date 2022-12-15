package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Hop;

import java.sql.SQLException;

public interface HopService {

    HopEntity getHop(String code) throws SQLException;

    Hop getWarehouse(String code) throws SQLException;
}
