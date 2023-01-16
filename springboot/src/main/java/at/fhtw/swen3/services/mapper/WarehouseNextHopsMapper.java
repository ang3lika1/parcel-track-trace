package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WarehouseNextHopsMapper {


    public WarehouseNextHops mapToTarget(WarehouseNextHopsEntity entity, Hop hop) {
        return WarehouseNextHops.builder()
                .traveltimeMins(entity.getTraveltimeMins())
                .hop(hop)
                .build();
    }

    public WarehouseNextHopsEntity mapToSource(WarehouseNextHops dto, HopEntity hop) {
        return WarehouseNextHopsEntity.builder()
                .traveltimeMins(dto.getTraveltimeMins())
                .hop(hop)
                .build();
    }
}
