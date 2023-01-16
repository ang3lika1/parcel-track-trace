package at.fhtw.swen3.services.kafka;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "stateTrackingInformation", groupId = "notification")
    public void listenGroupNotification(String message) {
        System.out.println("Received Message in group notification: " + message);
    }

}
