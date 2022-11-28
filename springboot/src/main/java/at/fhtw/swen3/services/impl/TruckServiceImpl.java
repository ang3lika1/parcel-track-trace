package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.services.TruckService;
import at.fhtw.swen3.services.mapper.TruckMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TruckServiceImpl implements TruckService {
    private final TruckMapper truckMapper;
    private final Validator validator;
    private final TruckRepository truckRepository;

}
