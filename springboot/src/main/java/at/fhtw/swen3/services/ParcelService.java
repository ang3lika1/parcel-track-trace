package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface ParcelService {

    NewParcelInfo saveNewParcel(Parcel parcel);

    TrackingInformation trackParcel(String trackingId) throws SQLException;

    ParcelEntity getParcel(String trackingId) throws SQLException;

    ParcelEntity reportParcelDelivery(String trackingId) throws SQLException;

    void changeHopArrival(ParcelEntity parcel, HopArrivalEntity hopArrival, HopEntity hop);

    public ResponseEntity<NewParcelInfo> saveExistingParcel(String trackingId, Parcel parcel) throws SQLException;

}
