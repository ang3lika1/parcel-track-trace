package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.HopArrivalRepository;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.mapper.HopArrivalMapper;
import at.fhtw.swen3.services.validation.Validator;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

@Slf4j
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
        trackingInformation.setState(TrackingInformation.StateEnum.PICKUP);



        //ParcelEntity parcelEntity = parcelMapper.from(parcel,null, null);
        ParcelEntity parcelEntity = parcelMapper.from(parcel, newParcelInfo, trackingInformation);

        parcelEntity = parcelRepository.save(parcelEntity);

        return parcelMapper.toParcelDto(parcelEntity);
    }

    @Override
    public TrackingInformation trackParcel(String trackingId) throws SQLException {
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        if (parcelEntity == null) {
            log.error("ölaksde");
            return null;
        }

        return parcelMapper.toTrackingInfoDto(parcelEntity);
    }

    public ParcelEntity getParcel(String trackingId) throws SQLException{
        return parcelRepository.findByTrackingId(trackingId);
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
