package com.tracktive.userservice.kafka;

import com.tracktive.userservice.exception.FailedToSendEventException;
import com.tracktive.userservice.model.DTO.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import userEvents.UserCreatedEvent;
import userEvents.UserDeletedEvent;
import userEvents.UserRole;

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

    public void sendUserCreatedEvent(UserDTO userDTO){

        log.info("UserEventProducer(USER_CREATED): Prepare user created event with user ID {}", userDTO.getId());

        UserCreatedEvent event = UserCreatedEvent.newBuilder()
                .setUserId(userDTO.getId())
                .setEmail(userDTO.getEmail())
                .setRole(UserRole.valueOf(userDTO.getUserRole().name()))
                .build();

        try {
            kafkaTemplate.send("user-created", event.toByteArray());

            log.info("UserEventProducer(USER_CREATED): Sent user created event with user ID {}", userDTO.getId());

        } catch (Exception e) {

            log.info("UserEventProducer(USER_CREATED): Failed to send user created event with user ID {}", userDTO.getId());

            throw new FailedToSendEventException("Failed to send user created event with user ID " + userDTO.getId(), e);
        }
    }

    public void sendUserDeletedEvent(String userId){

        log.info("UserEventProducer(USER_DELETED): Prepare user deleted event with user ID {}", userId);

        UserDeletedEvent event = UserDeletedEvent.newBuilder()
                .setUserId(userId)
                .build();

        try {
            kafkaTemplate.send("user-deleted", event.toByteArray());

            log.info("UserEventProducer(USER_DELETED): Sent user deleted event with user ID {}", userId);

        } catch (Exception e) {

            log.info("UserEventProducer(USER_DELETED): Failed to send user deleted event with user ID {}", userId);

            throw new FailedToSendEventException("Failed to send user deleted event with user ID " + userId, e);
        }
    }
}
