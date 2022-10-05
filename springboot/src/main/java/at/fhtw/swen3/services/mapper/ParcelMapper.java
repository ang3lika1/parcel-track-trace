package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfoDto;
import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ParcelMapper{
    @Mapping(source = "trackingInformation.state", target = "deliveryStatus")
    ParcelEntity from(ParcelDto parcel, NewParcelInfoDto newParcelInfo, TrackingInformationDto trackingInformation);
}
