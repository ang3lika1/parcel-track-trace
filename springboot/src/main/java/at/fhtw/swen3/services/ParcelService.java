package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.dto.TrackingInformationDto;

public interface ParcelService {

    ParcelDto saveNewParcel(ParcelDto parcel);

    TrackingInformationDto trackParcel(String trackingId);

}
