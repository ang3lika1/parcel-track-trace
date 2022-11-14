package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.validation.Validator;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    private final ParcelMapper parcelMapper;
    private final Validator validator;

    private final ParcelRepository parcelRepository;

    @Override
    public Parcel saveNewParcel(Parcel parcel) {
        validator.validate(parcel);
        // TODO: create trackingID

        NewParcelInfo newParcelInfo = NewParcelInfo.builder().build();
        TrackingInformation trackingInformation = TrackingInformation.builder().build();

        //ParcelEntity parcelEntity = parcelMapper.from(parcel,null, null);
        ParcelEntity parcelEntity = parcelMapper.from(parcel, newParcelInfo, trackingInformation);

        parcelEntity = parcelRepository.save(parcelEntity);

        return parcelMapper.toParcelDto(parcelEntity);
    }

    @Override
    public TrackingInformation trackParcel(String trackingId) {
        return parcelMapper.toTrackingInfoDto(parcelRepository.findByTrackingId(trackingId));
    }
}
