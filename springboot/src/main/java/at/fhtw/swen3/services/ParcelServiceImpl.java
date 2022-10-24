package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entity.EntityValidator;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.repository.ParcelRepository;
import at.fhtw.swen3.services.dto.NewParcelInfoDto;
import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.RequiredArgsConstructor;

import javax.annotation.Resource;

@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService{

    private final ParcelMapper parcelMapper;
    private final Validator validator;
    @Resource
    private ParcelRepository parcelRepository;

    @Override
    public ParcelDto saveNewParcel(ParcelDto parcel) {
        validator.validate(parcel);
        // TODO: create trackingID

        NewParcelInfoDto newParcelInfoDto = NewParcelInfoDto.builder().build();
        TrackingInformationDto trackingInformationDto = TrackingInformationDto.builder().build();

        ParcelEntity parcelEntity = parcelMapper.from(parcel, newParcelInfoDto, trackingInformationDto);

        parcelEntity = parcelRepository.save(parcelEntity);

        return parcelMapper.toParcelDto(parcelEntity);
    }

}
