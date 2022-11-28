package at.fhtw.swen3.services.impl;


import at.fhtw.swen3.persistence.repositories.GeoCoordinateRepository;
import at.fhtw.swen3.services.GeoCoordinateService;
import at.fhtw.swen3.services.mapper.GeoCoordinateMapper;
import at.fhtw.swen3.services.validation.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeoCoordinateServiceImpl implements GeoCoordinateService {
    private final Validator validator;
    private final GeoCoordinateMapper geoCoordinateMapper;
    private final GeoCoordinateRepository geoCoordinateRepository;

}
