package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WarehouseNextHopsMapper extends AbstractMapper<WarehouseNextHopsEntity, WarehouseNextHops>{
    private final HopMapper hopMapper;
    @Override
    public WarehouseNextHops mapToTarget(WarehouseNextHopsEntity entity) {
        return WarehouseNextHops.builder()
                .traveltimeMins(entity.getTraveltimeMins())
                .hop(hopMapper.mapToTarget(entity.getHop()))
                .build();
    }

    @Override
    public WarehouseNextHopsEntity mapToSource(WarehouseNextHops dto) {
        return WarehouseNextHopsEntity.builder()
                .traveltimeMins(dto.getTraveltimeMins())
                .hop(hopMapper.mapToSource(dto.getHop()))
                .build();
    }
}
