package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.ResetService;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final HopMapper hopMapper;
    private final WarehouseMapper warehouseMapper;
    private final Validator validator;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseNextHopsRepository warehouseNextHopsRepository;
    private final ResetService resetService;


    @Override
    public Warehouse exportWarehouses() {
         WarehouseEntity warehouseEntityHierarchy = warehouseRepository.findFirstByIdIsNotNullOrderByNextHops();
         if(warehouseEntityHierarchy==null) return null;
         return (Warehouse) hopMapper.mapToTarget(warehouseEntityHierarchy);
    }

    @Override
    public Hop getWarehouse(String code) throws SQLException {
        WarehouseEntity warehouseEntity = warehouseRepository.findByCode(code);
        if(warehouseEntity == null) return null;
        return hopMapper.mapToTarget(warehouseEntity);
    }

    @Override
    public Warehouse importWarehouses(Warehouse warehouse) {
        //validate data
        validator.validate(warehouse);

        //clear
        resetService.resetDB();

        WarehouseEntity warehouseEntity = (WarehouseEntity) hopMapper.mapToSource(warehouse);
        log.warn(warehouseEntity.toString());
        WarehouseEntity createdWarehouseEntity = warehouseRepository.save(warehouseEntity);
        warehouseNextHopsRepository.saveAll(warehouseEntity.getNextHops());
        return warehouseMapper.mapToTarget(createdWarehouseEntity);
    }
}
