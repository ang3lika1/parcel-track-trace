package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import at.fhtw.swen3.services.dto.Warehouse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransferwarehouseMapper extends AbstractMapper<TransferwarehouseEntity, Transferwarehouse>{
    private final GeoCoordinateMapper geoCoordinateMapper ;

    @Override
    public Transferwarehouse mapToTarget(TransferwarehouseEntity entity) {
        return Transferwarehouse.builder()
                .regionGeoJson(entity.getRegionGeoJson())
                .logisticsPartner(entity.getLogisticsPartner())
                .logisticsPartnerUrl(entity.getLogisticsPartnerUrl())
                .hopType(entity.getHopType())
                .code(entity.getCode())
                .description(entity.getDescription())
                .processingDelayMins(entity.getProcessingDelayMins())
                .locationName(entity.getLocationName())
                .locationCoordinates(geoCoordinateMapper.mapToTarget(entity.getLocationCoordinates()))
                .build();
    }

    @Override
    public TransferwarehouseEntity mapToSource(Transferwarehouse dto) {
        return TransferwarehouseEntity.builder()
                .regionGeoJson(dto.getRegionGeoJson())
                .logisticsPartner(dto.getLogisticsPartner())
                .logisticsPartnerUrl(dto.getLogisticsPartnerUrl())
                .hopType(dto.getHopType())
                .code(dto.getCode())
                .description(dto.getDescription())
                .processingDelayMins(dto.getProcessingDelayMins())
                .locationName(dto.getLocationName())
                .locationCoordinates(geoCoordinateMapper.mapToSource(dto.getLocationCoordinates()))
                .build();
    }
}
