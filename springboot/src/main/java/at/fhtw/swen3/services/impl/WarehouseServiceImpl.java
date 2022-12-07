package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;

@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseMapper warehouseMapper;
    private final Validator validator;
    private final WarehouseRepository warehouseRepository;


    @Override
    public Warehouse exportWarehouses() {
        return null;
    }

    @Override
    public Hop getWarehouse(String code) throws SQLException {
        WarehouseEntity warehouseEntity = warehouseRepository.findByCode(code);
        if(warehouseEntity == null) return null;
        return warehouseMapper.mapToTarget(warehouseEntity);
    }

    @Override
    public Warehouse importWarehouses(Warehouse warehouse) {
        validator.validate(warehouse);
        WarehouseEntity warehouseEntity = warehouseMapper.mapToSource(warehouse);
        warehouseEntity = warehouseRepository.save(warehouseEntity);
        return warehouseMapper.mapToTarget(warehouseEntity);
    }
}
