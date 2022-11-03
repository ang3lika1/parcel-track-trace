package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.repository.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.Validator;
import at.fhtw.swen3.services.dto.NewParcelInfoDto;
import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    private final ParcelMapper parcelMapper;
    private final Validator validator;

    private final ParcelRepository parcelRepository;

    @Override
    public ParcelDto saveNewParcel(ParcelDto parcel) {
        validator.validate(parcel);
        // TODO: create trackingID

        NewParcelInfoDto newParcelInfoDto = NewParcelInfoDto.builder().build();
        TrackingInformationDto trackingInformationDto = TrackingInformationDto.builder().build();

        //ParcelEntity parcelEntity = parcelMapper.from(parcel,null, null);
        ParcelEntity parcelEntity = parcelMapper.from(parcel,newParcelInfoDto, trackingInformationDto);

        parcelEntity = parcelRepository.save(parcelEntity);

        return parcelMapper.toParcelDto(parcelEntity);
    }

    @Override
    public TrackingInformationDto trackParcel(String trackingId) {
        return parcelMapper.toTrackingInfoDto(parcelRepository.findByTrackingId(trackingId));
    }
}
