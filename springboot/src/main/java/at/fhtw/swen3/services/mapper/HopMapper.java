package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
public class HopMapper  extends AbstractMapper<HopEntity, Hop>{
    private final GeoCoordinateMapper geoCoordinateMapper;
    //private final WarehouseMapper warehouseMapper;
    @Override
    public Hop mapToTarget(HopEntity entity) {
        return Hop.builder().code(entity.getCode()).description(entity.getDescription()).hopType(entity.getHopType()).locationName(entity.getLocationName()).processingDelayMins(entity.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates())).build();
    }

    @Override
    public HopEntity mapToSource(Hop dto) {
        if(dto instanceof Truck){
            return TruckEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).regionGeoJson(((Truck) dto).getRegionGeoJson()).numberPlate(((Truck) dto).getNumberPlate()).build();
        }
        if(dto instanceof Warehouse){
            //return warehouseMapper.mapToSource((Warehouse) dto);
            //return WarehouseEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).level(((Warehouse) dto).getLevel()).nextHops(warehouseNextHopsMapper.mapToSource(((Warehouse) dto).getNextHops())).build();
            return WarehouseEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).level(((Warehouse) dto).getLevel()).build();
        }
        log.warn("HopEntity mapped");
        return HopEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).build();
    }
    public HopEntity mapToSource(Truck dto) {
        return TruckEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).regionGeoJson(dto.getRegionGeoJson()).numberPlate(dto.getNumberPlate()).build();
    }
}
