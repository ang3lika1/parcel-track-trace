package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class OpenStreetMapEncodingProxyTest {
    OpenStreetMapEncodingProxy proxy = new OpenStreetMapEncodingProxy();

    @Test
    void encodeAddressTrue() throws UnsupportedEncodingException {
        String testAddress = "Engerthstrasse+228";
        GeoCoordinate geocoordinate = proxy.encodeAddress(testAddress);
        log.info("test - lat:" + geocoordinate.getLat() + ", lon:" + geocoordinate.getLon());
        assertEquals(geocoordinate.getLat(), 48.2163914);
        assertEquals(geocoordinate.getLon(), 16.4141428);
    }
}