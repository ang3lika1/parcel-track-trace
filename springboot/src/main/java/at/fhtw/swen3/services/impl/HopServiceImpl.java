package at.fhtw.swen3.services.impl;


import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.services.HopService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;

@RequiredArgsConstructor
public class HopServiceImpl implements HopService {
    private final Validator validator;
    private final HopMapper hopMapper;
    private final HopRepository hopRepository;

    @Override
    public HopEntity getHop(String code) throws SQLException {
        return hopRepository.findByCode(code);
    }

    @Override
    public Hop getWarehouse(String code) throws SQLException {
        HopEntity hopEntity = hopRepository.findByCode(code);
        if(hopEntity == null) return null;
        return hopMapper.mapToTarget(hopEntity);
    }


}
