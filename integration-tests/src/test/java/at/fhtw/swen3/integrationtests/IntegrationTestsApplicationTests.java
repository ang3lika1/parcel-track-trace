package at.fhtw.swen3.integrationtests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class IntegrationTestsApplicationTests {

    @Value("$testing_env_url")
    private String testing_env_url;
    private static HttpClient client;

    @BeforeAll
    static void init() {
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .authenticator(Authenticator.getDefault())
                .build();
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void submitParcelTest() throws IOException, InterruptedException {

        String parcelJson = """
                {
                  "weight": 0.1,
                  "recipient": {
                     "name": "Clemens Rinner",
                     "street": "Heigerleinstraße 38/38",
                     "postalCode": "A-1030",
                     "city": "Wien",
                     "country": "Österreich"
                   },
                  "sender": {
                     "name": "David Kenner",
                     "street": "Baumgasse 60/70",
                     "postalCode": "A-1160",
                     "city": "Wien",
                     "country": "Österreich"
                  }
                }""";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(testing_env_url))
                .POST(HttpRequest.BodyPublishers.ofString(parcelJson))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.warn(response.body());
    }
}
