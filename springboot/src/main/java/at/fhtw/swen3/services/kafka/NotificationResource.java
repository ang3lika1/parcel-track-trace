package at.fhtw.swen3.services.kafka;

import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parcel")
@Slf4j
public class NotificationResource {
    private final ParcelService parcelService;

    public NotificationResource(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @PostMapping("/submitParcel")
    public ResponseEntity<Void> sendNotification(@RequestBody TrackingInformation state) {
        try {
            System.out.println("notififcationres" + state);
            parcelService.send(state);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
