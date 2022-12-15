package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.ResetService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResetServiceImpl implements ResetService {
    private final GeoCoordinateRepository geoCoordinateRepository;
    private final HopArrivalRepository hopArrivalRepository;
    private final HopRepository hopRepository;
    private final ParcelRepository parcelRepository;
    private final RecipientRepository recipientRepository;
    private final WarehouseNextHopsRepository warehouseNextHopsRepository;

    @Override
    public void resetDB() {
        hopArrivalRepository.deleteAll();
        recipientRepository.deleteAll();
        parcelRepository.deleteAll();
        warehouseNextHopsRepository.deleteAll();
        hopRepository.deleteAll();
        geoCoordinateRepository.deleteAll();
    }
}
