package at.fhtw.swen3.services.impl;


import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.services.HopService;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HopServiceImpl implements HopService {
    private final Validator validator;
    private final HopMapper hopMapper;
    private final HopRepository hopRepository;

}
