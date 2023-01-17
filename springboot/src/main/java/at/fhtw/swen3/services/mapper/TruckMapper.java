package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;

public class TruckMapper extends AbstractMapper<TruckEntity, Truck>{
    @Override
    public Truck mapToTarget(TruckEntity entity) {

        return null;
       //return Truck.builder().regionGeoJson(entity.getRegionGeoJson()).numberPlate(entity.getNumberPlate()).build();
        //return Truck.builder().locationCoordinates(entity.getLocationCoordinates()).numberPlate(entity.getNumberPlate()).build();
    }

    @Override
    public TruckEntity mapToSource(Truck dto) {
        return TruckEntity.builder().regionGeoJson(dto.getRegionGeoJson()).numberPlate(dto.getNumberPlate()).build();
    }
}
