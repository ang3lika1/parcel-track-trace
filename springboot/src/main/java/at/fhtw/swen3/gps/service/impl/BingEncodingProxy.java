package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.HttpDataHandler;
import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;


@Slf4j
public class BingEncodingProxy implements GeoEncodingService {

    @Override
    public GeoCoordinate encodeAddress(String addr) {
        try {
            //HttpDataHandler http = new HttpDataHandler();
            String requesturl = "https://nominatim.openstreetmap.org/search.php?q=\"" + URLEncoder.encode(addr, "UTF-8")+ "\"&format=json";
            log.info(requesturl);
            //JsonNode jsonNode = HttpDataHandler.get(requesturl);

            //JsonNode latNode, lonNode;
            String jsonString = HttpDataHandler.httpGetJsonString(requesturl);


            double lat, lon = 0;

            //latNode = jsonNode.get("lat");
            //lonNode = jsonNode.get("lat");

            //lat = latNode.asDouble();
            lat = HttpDataHandler.getDoubleFromJson(jsonString, "lat");
            lon = HttpDataHandler.getDoubleFromJson(jsonString, "lon");
            log.info("LAT:" + lat + ";LNG:" + lon);

            return new GeoCoordinate(lat, lon);

        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
    }




   /* @Override
    public GeoCoordinate encodeAddress(String addr) {
        try {
            //String surl = "https://maps.googleapis.com/maps/api/geocode/json?address="+ URLEncoder.encode(addr, "UTF-8")+"&key="+API_KEY;
            String surl = "http://nominatim.openstreetmap.org/search.php?q="+ URLEncoder.encode(addr, "UTF-8")+"&format=json";
            URL url = new URL(surl);
            InputStream is = url.openConnection().getInputStream();

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject jo = new JSONObject(responseStrBuilder.toString());
            JSONArray results = jo.getJSONArray("results");
            double lat = 0;
            double lng = 0;
            String zip = null;
            String city = null;
            String region = null;
            String province = null;

            if(results.length() > 0) {
                JSONObject jsonObject;
                jsonObject = results.getJSONObject(0);
                lat = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                lng = jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                log.info("LAT:"+lat+";LNG:"+lng);

                JSONArray ja = jsonObject.getJSONArray("address_components");
                for(int l=0; l<ja.length(); l++) {
                    JSONObject curjo = ja.getJSONObject(l);
                    String type = curjo.getJSONArray("types").getString(0);
                    String short_name = curjo.getString("short_name");
                    if(type.equals("postal_code")) {
                        log.info("POSTAL_CODE: "+short_name);
                        zip = short_name;
                    }
                    else if(type.equals("administrative_area_level_3")) {
                        log.info("CITY: "+short_name);
                        city = short_name;
                    }
                    else if(type.equals("administrative_area_level_2")) {
                        log.info("PROVINCE: "+short_name);
                        province = short_name;
                    }
                    else if(type.equals("administrative_area_level_1")) {
                        log.info("REGION: "+short_name);
                        region = short_name;
                    }
                }
                return new GeoCoordinate(lat, lng);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
