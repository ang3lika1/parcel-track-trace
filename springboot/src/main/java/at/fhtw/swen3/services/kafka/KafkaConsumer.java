package at.fhtw.swen3.services.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "stateTrackingInformation", groupId = "notification")
    public void listenGroupNotification(String message) {
        System.out.println("Received Message in group notification: " + message);
    }
}
