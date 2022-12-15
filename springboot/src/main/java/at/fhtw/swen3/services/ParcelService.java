package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;

import java.sql.SQLException;

public interface ParcelService {

    Parcel saveNewParcel(Parcel parcel);

    TrackingInformation trackParcel(String trackingId) throws SQLException;

    ParcelEntity getParcel(String trackingId) throws SQLException;

    ParcelEntity reportParcelDelivery(String trackingId) throws SQLException;

    void changeHopArrival(ParcelEntity parcel, HopArrivalEntity hopArrival, HopEntity hop);

}
