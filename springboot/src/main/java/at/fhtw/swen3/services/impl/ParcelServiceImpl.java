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
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

@RequiredArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {
    private final ParcelMapper parcelMapper;
    private final Validator validator;
    private final ParcelRepository parcelRepository;

    private String generateTrackingId() throws SQLException {
        String newId;
        do {
            newId = RandomStringUtils.randomAlphanumeric(9).toUpperCase();
        } while (parcelRepository.findByTrackingId(newId) != null);

        return newId;
    }

    @Override
    public NewParcelInfo saveNewParcel(Parcel parcel) {
        validator.validate(parcel);

        String trackingId = null;
        try {
            trackingId = generateTrackingId();
        } catch (SQLException e) {
            log.error("error while creating tracking id: " + e.getMessage());
        }

        NewParcelInfo newParcelInfo = NewParcelInfo.builder().trackingId(trackingId).build();
        TrackingInformation trackingInformation = TrackingInformation.builder().build();
        trackingInformation.setState(TrackingInformation.StateEnum.PICKUP);

        ParcelEntity parcelEntity = parcelMapper.from(parcel, newParcelInfo, trackingInformation);

        parcelEntity = parcelRepository.save(parcelEntity);

        return parcelMapper.toParcelInfoDto(parcelEntity);
    }

    @Override
    public TrackingInformation trackParcel(String trackingId) throws SQLException {
        validator.validate(trackingId);
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        if (parcelEntity == null) {
            log.warn("returned parcelEntity from DB while tracking parcel is null");
            return null;
        }

        return parcelMapper.toTrackingInfoDto(parcelEntity);
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

    public ResponseEntity<NewParcelInfo> saveExistingParcel(String trackingId, Parcel parcel) throws SQLException {
        NewParcelInfo newParcelInfo = NewParcelInfo.builder().trackingId(trackingId).build();
        try {
            validator.validate(newParcelInfo);
            validator.validate(parcel);
        } catch (ConstraintViolationException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        if (parcelEntity != null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        parcelEntity = parcelMapper.from(parcel, newParcelInfo, TrackingInformation.builder().state(TrackingInformation.StateEnum.PICKUP).build());

        parcelRepository.save(parcelEntity);
        return new ResponseEntity<>(newParcelInfo, HttpStatus.OK);
    }

}
