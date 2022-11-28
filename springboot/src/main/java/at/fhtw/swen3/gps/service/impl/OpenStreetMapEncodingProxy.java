package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.HttpDataHandler;
import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;

import java.io.IOException;
import java.net.URLEncoder;


@Slf4j
public class OpenStreetMapEncodingProxy implements GeoEncodingService {

    @Override
    public GeoCoordinate encodeAddress(String addr) {
        try {
            String requesturl = "https://nominatim.openstreetmap.org/search.php?q=\"" + URLEncoder.encode(addr, "UTF-8")+ "\"&format=json";
            log.info("request url: " + requesturl);

            String jsonString = HttpDataHandler.httpGetJsonString(requesturl);

            double lat, lon = 0;
            lat = HttpDataHandler.getDoubleFromJson(jsonString, "lat");
            lon = HttpDataHandler.getDoubleFromJson(jsonString, "lon");
            log.info("coordinates from openstreetmap json - LAT:" + lat + ", LON:" + lon);

            return new GeoCoordinate(lat, lon);

        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
