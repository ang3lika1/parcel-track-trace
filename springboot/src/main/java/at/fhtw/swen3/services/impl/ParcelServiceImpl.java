package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@RequiredArgsConstructor
@Slf4j
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
        trackingInformation.setState(TrackingInformation.StateEnum.PICKUP);



        //ParcelEntity parcelEntity = parcelMapper.from(parcel,null, null);
        ParcelEntity parcelEntity = parcelMapper.from(parcel, newParcelInfo, trackingInformation);

        parcelEntity = parcelRepository.save(parcelEntity);

        return parcelMapper.toParcelDto(parcelEntity);
    }

    @Override
    public TrackingInformation trackParcel(String trackingId) throws SQLException {
        return parcelMapper.toTrackingInfoDto(parcelRepository.findByTrackingId(trackingId));
    }

    public ParcelEntity getParcel(String trackingId) throws SQLException{
        return parcelRepository.findByTrackingId(trackingId);
    }

    @Override
    public ParcelEntity reportParcelDelivery(String trackingId) throws SQLException {
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        if(parcelEntity == null) return null;

        parcelEntity.setDeliveryStatus(TrackingInformation.StateEnum.DELIVERED);
        ParcelEntity updatedParcel = parcelRepository.save(parcelEntity);
        log.info(String.valueOf(updatedParcel));
        System.out.println(updatedParcel);
        return updatedParcel;
    }

    public void changeHopArrival(ParcelEntity parcel, HopArrivalEntity hopArrival, HopEntity hop) {
        parcel.removeFutureHop(hopArrival);
        parcel.addVisitedHop(hopArrival);

        switch (hop.getHopType()) {
            case "Warehouse":
                parcel.setDeliveryStatus(TrackingInformation.StateEnum.INTRANSPORT);
                break;

            case "Truck":
                parcel.setDeliveryStatus(TrackingInformation.StateEnum.INTRUCKDELIVERY);
                break;

            case "TransferWarehouse":
                //TODO: call logistics partner and transfer parcel
                parcel.setDeliveryStatus(TrackingInformation.StateEnum.TRANSFERRED);
                break;
        }

        parcelRepository.save(parcel);
    }

}
