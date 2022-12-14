package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseNextHopsService;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseMapper warehouseMapper;
    private final Validator validator;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseNextHopsRepository warehouseNextHopsRepository;


    @Override
    public List<Warehouse> exportWarehouses() {
         List<WarehouseEntity> warehouseEntityHierarchy = warehouseRepository.findAll();
         if(warehouseEntityHierarchy.equals(Collections.emptyList())) return null;
         return warehouseMapper.mapToTarget(warehouseEntityHierarchy);
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
        WarehouseEntity createdWarehouseEntity = warehouseRepository.save(warehouseEntity);
        warehouseNextHopsRepository.saveAll(warehouseEntity.getNextHops());
        return warehouseMapper.mapToTarget(createdWarehouseEntity);
    }
}
