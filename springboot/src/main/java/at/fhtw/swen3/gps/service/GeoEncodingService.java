package at.fhtw.swen3.gps.service;

import at.fhtw.swen3.services.dto.GeoCoordinate;

import java.io.UnsupportedEncodingException;

public interface GeoEncodingService {
    GeoCoordinate encodeAddress(String a) throws UnsupportedEncodingException;
}
