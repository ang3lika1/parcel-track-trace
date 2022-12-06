package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopEntity;

import java.sql.SQLException;

public interface HopService {

    HopEntity getHop(String code) throws SQLException;
}
