package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Warehouse;

public class WarehouseMapper extends AbstractMapper<WarehouseEntity, Warehouse>{
    @Override
    public Warehouse mapToTarget(WarehouseEntity entity) {
        return Warehouse.builder().level(entity.getLevel()).build();
    }

    @Override
    public WarehouseEntity mapToSource(Warehouse dto) {
        return WarehouseEntity.builder().level(dto.getLevel()).build();
    }
}
