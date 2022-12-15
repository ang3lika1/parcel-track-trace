package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.HopArrivalRepository;
import at.fhtw.swen3.services.HopArrivalService;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.mapper.HopArrivalMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@RequiredArgsConstructor
public class HopArrivalServiceImpl implements HopArrivalService {
    private final Validator validator;
    private final HopArrivalMapper hopArrivalMapper;
    private final HopArrivalRepository hopArrivalRepository;


    @Override
    public HopArrival reportDelivery(String code, String description) {
        HopArrivalEntity hopArrivalEntity = HopArrivalEntity.builder().code(code).description(description).dateTime(OffsetDateTime.from(LocalDateTime.now())).build();
        validator.validate(hopArrivalEntity);
        return hopArrivalMapper.mapToTarget(hopArrivalRepository.save(hopArrivalEntity));
    }

    @Override
    public void reportHopArrival(String trackingId, String code) {

    }


}
