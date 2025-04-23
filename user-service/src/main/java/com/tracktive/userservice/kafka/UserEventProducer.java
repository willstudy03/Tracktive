package com.tracktive.userservice.kafka;

import com.tracktive.userservice.exception.FailedToSendEventException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import userEvents.UserCreatedEvent;

/**
* Description: User Event Producer
* @author William Theo
* @date 23/4/2025
*/
@Service
public class UserEventProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    private static final Logger log = LoggerFactory.getLogger(UserEventProducer.class);

    @Autowired
    public UserEventProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreatedEvent(String userId, String email){

        log.info("UserEventProducer(STOCK_DEDUCTION_SUCCESS_EVENT): Prepare user created event with user ID {}", userId);

        UserCreatedEvent event = UserCreatedEvent.newBuilder()
                .setUserId(userId)
                .setEmail(email)
                .build();

        try {
            kafkaTemplate.send("user-created", event.toByteArray());

            log.info("UserEventProducer(USER_CREATED): Sent user created event with user ID {}", userId);

        } catch (Exception e) {

            log.info("UserEventProducer(USER_CREATED): Failed to send user created event with user ID {}", userId);

            throw new FailedToSendEventException("Failed to send user created event with user ID " + userId, e);
        }
    }
}
