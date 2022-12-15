package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.HopArrival;

public interface HopArrivalService {
    HopArrival reportDelivery(String trackingId, String description);

    void reportHopArrival(String trackingId, String code);
}
