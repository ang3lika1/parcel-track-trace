package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;

public interface ParcelService {

    Parcel saveNewParcel(Parcel parcel);

    TrackingInformation trackParcel(String trackingId);

}
