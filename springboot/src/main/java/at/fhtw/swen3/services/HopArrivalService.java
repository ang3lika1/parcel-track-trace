package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.HopArrival;

public interface HopArrivalService {
    HopArrival reportDelivery(String trackingId, String description);
}
