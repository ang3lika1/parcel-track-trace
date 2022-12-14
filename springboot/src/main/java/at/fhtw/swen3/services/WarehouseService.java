package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;

import java.sql.SQLException;
import java.util.List;

public interface WarehouseService {
    List<Warehouse> exportWarehouses ();
    Hop getWarehouse(String code) throws SQLException;
    Warehouse importWarehouses(Warehouse warehouse);
}
