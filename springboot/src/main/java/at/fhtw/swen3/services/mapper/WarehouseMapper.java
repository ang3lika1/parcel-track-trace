package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
@RequiredArgsConstructor
public class WarehouseMapper extends AbstractMapper<WarehouseEntity, Warehouse>{
    @Override
    public Warehouse mapToTarget(WarehouseEntity object) {
        return null;
    }

    @Override
    public WarehouseEntity mapToSource(Warehouse object) {
        return null;
    }

    //private final WarehouseNextHopsMapper warehouseNextHopsMapper;
    //private final GeoCoordinateMapper geoCoordinateMapper ;
    //@Override
    //public Warehouse mapToTarget(WarehouseEntity entity) {
    //    return Warehouse.builder()
    //            .level(entity.getLevel())
    //            .nextHops(warehouseNextHopsMapper.mapToTarget(entity.getNextHops()))
    //            .hopType(entity.getHopType())
    //            .code(entity.getCode())
    //            .description(entity.getDescription())
    //            .processingDelayMins(entity.getProcessingDelayMins())
    //            .locationName(entity.getLocationName())
    //            .locationCoordinates(geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates()))
    //            .build();
    //}
//
    //@Override
    //public WarehouseEntity mapToSource(Warehouse dto) {
//
    //    return WarehouseEntity.builder()
    //            .level(dto.getLevel())
    //            .nextHops(warehouseNextHopsMapper.mapToSource(dto.getNextHops()))
    //            .hopType(dto.getHopType())
    //            .code(dto.getCode())
    //            .description(dto.getDescription())
    //            .processingDelayMins(dto.getProcessingDelayMins())
    //            .locationName(dto.getLocationName())
    //            .locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates()))
    //            .build();
    //}
}
