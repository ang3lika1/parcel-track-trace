package at.fhtw.swen3.gps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class HttpDataHandler {
    private static JsonNode getJsonObj(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(json);
    }

    public static double getDoubleFromJson(String json, String gets) throws JsonProcessingException {
        //log.info("json: " + getJsonObj(json).findParent(gets));
        return getJsonObj(json).findParent(gets).get(gets).asDouble();
    }

    public static String httpGetJsonString(String url) throws IOException {
        HttpURLConnection connection = null;
        try {
            URL realUrl = new URL(url);
            connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            StringBuilder stringBuilder;

            int status = connection.getResponseCode();
            log.info("http response code: " + status);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                stringBuilder = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(System.lineSeparator());
                }
            }
            return stringBuilder.toString();
        } finally {
            assert connection != null;
            connection.disconnect();
        }
    }
}
