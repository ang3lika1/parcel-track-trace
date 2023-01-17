package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/*
TODO:
   - Hop to abstract class
   - test HopMapper
   - adapt mapToTarget
   - integration tests
   -
 */
@RequiredArgsConstructor
@Slf4j
public class HopMapper  extends AbstractMapper<HopEntity, Hop>{
    private final GeoCoordinateMapper geoCoordinateMapper;

    @Lazy
    private final WarehouseNextHopsMapper warehouseNextHopsMapper;

    @Override
    public Hop mapToTarget(HopEntity entity) {
        switch (entity.getHopType()) {
            case "Warehouse":
                WarehouseEntity warehouseEntity = (WarehouseEntity) entity;

                List<WarehouseNextHops> nextHops = new ArrayList<>();

                for (WarehouseNextHopsEntity warehouseNextHop : warehouseEntity.getNextHops()) {
                    Hop hop = mapToTarget(warehouseNextHop.getHop());
                    nextHops.add(warehouseNextHopsMapper.mapToTarget(warehouseNextHop, hop));
                }

                return new Warehouse(
                        entity.getHopType(),
                        entity.getCode(),
                        entity.getDescription(),
                        entity.getProcessingDelayMins(),
                        entity.getLocationName(),
                        geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates()),
                        warehouseEntity.getLevel(),
                        nextHops);


            case "Truck":
                //return truckMapper.mapToTarget((TruckEntity) entity);
                return new Truck(
                        entity.getHopType(),
                        entity.getCode(),
                        entity.getDescription(),
                        entity.getProcessingDelayMins(),
                        entity.getLocationName(),
                        geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates()),
                        ((TruckEntity) entity).getRegionGeoJson(),
                        ((TruckEntity) entity).getNumberPlate());
               // return Truck.builder().code(entity.getCode()).description(entity.getDescription()).hopType(entity.getHopType()).locationName(entity.getLocationName()).processingDelayMins(entity.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates())).regionGeoJson(((TruckEntity) entity).getRegionGeoJson()).numberPlate(((TruckEntity) entity).getNumberPlate()).build();
            case "TransferWarehouse":
                //return transferwarehouseMapper.mapToTarget((TransferwarehouseEntity) entity);
                return new Transferwarehouse(
                        entity.getHopType(),
                        entity.getCode(),
                        entity.getDescription(),
                        entity.getProcessingDelayMins(),
                        entity.getLocationName(),
                        geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates()),
                        ((TransferwarehouseEntity) entity).getRegionGeoJson(),
                        ((TransferwarehouseEntity) entity).getLogisticsPartner(),
                        ((TransferwarehouseEntity) entity).getLogisticsPartnerUrl()
                );
                //return Transferwarehouse.builder().code(entity.getCode()).description(entity.getDescription()).hopType(entity.getHopType()).locationName(entity.getLocationName()).processingDelayMins(entity.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates())).regionGeoJson(((TransferwarehouseEntity) entity).getRegionGeoJson()).logisticsPartner(((TransferwarehouseEntity) entity).getLogisticsPartner()).logisticsPartnerUrl(((TransferwarehouseEntity) entity).getLogisticsPartnerUrl()).build();
        }

        return new Hop(
                entity.getHopType(),
                entity.getCode(),
                entity.getDescription(),
                entity.getProcessingDelayMins(),
                entity.getLocationName(),
                geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates())
        );


       // return Hop.builder().code(entity.getCode()).description(entity.getDescription()).hopType(entity.getHopType()).locationName(entity.getLocationName()).processingDelayMins(entity.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates())).build();
    }

    @Override
    public HopEntity mapToSource(Hop dto) {
        if(dto instanceof Truck){
            return TruckEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).regionGeoJson(((Truck) dto).getRegionGeoJson()).numberPlate(((Truck) dto).getNumberPlate()).build();
        }
        if(dto instanceof Warehouse){
            Warehouse warehouse = (Warehouse) dto;
            List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();

            for (WarehouseNextHops warehouseNextHop : warehouse.getNextHops()) {
                HopEntity hopEntity = mapToSource(warehouseNextHop.getHop());
                nextHops.add(warehouseNextHopsMapper.mapToSource(warehouseNextHop, hopEntity));
            }

            //return warehouseMapper.mapToSource((Warehouse) dto);
            return WarehouseEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).level(((Warehouse) dto).getLevel()).nextHops(nextHops).build();
            //return WarehouseEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).level(((Warehouse) dto).getLevel()).build();
        }
        log.warn("HopEntity mapped");
        return HopEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).build();
    }

    public HopEntity mapToSource(Truck dto) {
        return TruckEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).regionGeoJson(dto.getRegionGeoJson()).numberPlate(dto.getNumberPlate()).build();
    }
}
