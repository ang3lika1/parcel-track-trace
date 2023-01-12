package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
public class HopMapper  extends AbstractMapper<HopEntity, Hop>{
    private final GeoCoordinateMapper geoCoordinateMapper;
    //private final WarehouseMapper warehouseMapper;
    //private final TruckMapper truckMapper;
    //private final TransferwarehouseMapper transferwarehouseMapper;

   // private final WarehouseNextHopsMapper warehouseNextHopsMapper;
    @Override
    public Hop mapToTarget(HopEntity entity) {
        switch (entity.getHopType()) {
            case "Warehouse":
                //return warehouseMapper.mapToTarget((WarehouseEntity) entity);
                //return Warehouse.builder().code(entity.getCode()).description(entity.getDescription()).hopType(entity.getHopType()).locationName(entity.getLocationName()).processingDelayMins(entity.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates())).nextHops(warehouseNextHopsMapper.mapToTarget(entity).getNextHops()).build();
            break;
            case "Truck":
                //return truckMapper.mapToTarget((TruckEntity) entity);
                return Truck.builder().code(entity.getCode()).description(entity.getDescription()).hopType(entity.getHopType()).locationName(entity.getLocationName()).processingDelayMins(entity.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates())).regionGeoJson(((TruckEntity) entity).getRegionGeoJson()).numberPlate(((TruckEntity) entity).getNumberPlate()).build();
            case "TransferWarehouse":
                //return transferwarehouseMapper.mapToTarget((TransferwarehouseEntity) entity);
                return Transferwarehouse.builder().code(entity.getCode()).description(entity.getDescription()).hopType(entity.getHopType()).locationName(entity.getLocationName()).processingDelayMins(entity.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates())).regionGeoJson(((TransferwarehouseEntity) entity).getRegionGeoJson()).logisticsPartner(((TransferwarehouseEntity) entity).getLogisticsPartner()).logisticsPartnerUrl(((TransferwarehouseEntity) entity).getLogisticsPartnerUrl()).build();
        }
        return Hop.builder().code(entity.getCode()).description(entity.getDescription()).hopType(entity.getHopType()).locationName(entity.getLocationName()).processingDelayMins(entity.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates())).build();
    }

    @Override
    public HopEntity mapToSource(Hop dto) {
        if(dto instanceof Truck){
            return TruckEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).regionGeoJson(((Truck) dto).getRegionGeoJson()).numberPlate(((Truck) dto).getNumberPlate()).build();
        }
        if(dto instanceof Warehouse){
            //return warehouseMapper.mapToSource((Warehouse) dto);

            //return WarehouseEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).level(((Warehouse) dto).getLevel())
            //.nextHops(warehouseNextHopsMapper.mapToSource(((Warehouse) dto).getNextHops())).build();
            return WarehouseEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).level(((Warehouse) dto).getLevel()).build();
        }
        if(dto instanceof Transferwarehouse){
            return TransferwarehouseEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).regionGeoJson(((Transferwarehouse) dto).getRegionGeoJson()).logisticsPartner(((Transferwarehouse) dto).getLogisticsPartner()).logisticsPartnerUrl(((Transferwarehouse) dto).getLogisticsPartnerUrl()).build();
        }
        log.warn("HopEntity mapped");
        return HopEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).build();
    }
    public HopEntity mapToSource(Truck dto) {
        return TruckEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).regionGeoJson(dto.getRegionGeoJson()).numberPlate(dto.getNumberPlate()).build();
    }
}
