package at.fhtw.swen3.services.kafka;

import at.fhtw.swen3.services.dto.TrackingInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("https://www.toptal.com/developers/postbin/1673902978298-9152060451451")
@RequestMapping("hello-world")
@Slf4j
public class KafkaProducerController {
    //for sending kafka message
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final ObjectMapper mapper = new ObjectMapper();

    @GetMapping(produces = "application/json")
    public String produce(TrackingInformation state) throws Exception {
        //kafka send message when state of parcel changes
        System.out.println("producercontroller:" + state);
        kafkaTemplate.send("stateTrackingInformation", toJson(state));
        return toJson(state);
    }


    private <T> String toJson(T object) throws Exception {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
