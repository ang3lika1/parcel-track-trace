package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Truck;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HopMapper  extends AbstractMapper<HopEntity, Hop>{
    private final GeoCoordinateMapper geoCoordinateMapper;
    @Override
    public Hop mapToTarget(HopEntity entity) {
        return Hop.builder().code(entity.getCode()).description(entity.getDescription()).hopType(entity.getHopType()).locationName(entity.getLocationName()).processingDelayMins(entity.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates())).build();
    }

    @Override
    public HopEntity mapToSource(Hop dto) {
        if(dto instanceof Truck){
            return TruckEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).regionGeoJson(((Truck) dto).getRegionGeoJson()).numberPlate(((Truck) dto).getNumberPlate()).build();
        }
        return HopEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).build();
    }
    public HopEntity mapToSource(Truck dto) {
        return TruckEntity.builder().code(dto.getCode()).description(dto.getDescription()).hopType(dto.getHopType()).locationName(dto.getLocationName()).processingDelayMins(dto.getProcessingDelayMins()).locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates())).regionGeoJson(dto.getRegionGeoJson()).numberPlate(dto.getNumberPlate()).build();
    }
}
