package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfoDto;
import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ParcelMapper{
    @Mapping(source = "trackingInformation.state", target = "deliveryStatus")
    ParcelEntity from(ParcelDto parcel, NewParcelInfoDto newParcelInfo, TrackingInformationDto trackingInformation);

    ParcelDto toParcelDto(ParcelEntity entity);

    NewParcelInfoDto toParcelInfoDto(ParcelEntity entity);

    @Mapping(source = "entity.deliveryStatus", target = "state")
    TrackingInformationDto toTrackingInfoDto(ParcelEntity entity);
}
