package com.tracktive.authservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import com.tracktive.authservice.model.DTO.UserCredentialRequestDTO;
import com.tracktive.authservice.model.Enum.UserRole;
import com.tracktive.authservice.service.UserCredentialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import userEvents.UserCreatedEvent;
import userEvents.UserDeletedEvent;

/**
* Description: User Event Consumer
* @author William Theo
* @date 23/4/2025
*/
@Service
public class UserEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(UserEventConsumer.class);
    private final UserCredentialService userCredentialService;

    private final TransactionTemplate transactionTemplate;

    @Autowired
    public UserEventConsumer(UserCredentialService userCredentialService, TransactionTemplate transactionTemplate) {
        this.userCredentialService = userCredentialService;
        this.transactionTemplate = transactionTemplate;
    }

    @KafkaListener(topics = "user-created", groupId = "auth-service")
    public void consumeUserCreatedEvent(byte[] event, Acknowledgment ack){
        boolean processSucceeded = false;

        try {
            UserCreatedEvent userCreatedEvent = UserCreatedEvent.parseFrom(event);
            String userId = userCreatedEvent.getUserId();
            String email = userCreatedEvent.getEmail();
            UserRole userRole = UserRole.valueOf(userCreatedEvent.getRole().name());

            log.info("UserEventConsumer(User_Created): Received user created event for [{}] user ID {} with email ID {}",
                    userRole, userId, email);

            userCredentialService.addUserCredential(new UserCredentialRequestDTO(userId, email, userRole));

            processSucceeded = true;
            log.info("Successfully created [{}] user credential {} with email {}", userRole, userId, email);

        } catch (InvalidProtocolBufferException e) {
            log.error("Deserialization error for user created event", e);
            // Deserialization errors are non-recoverable, so we should acknowledge
            processSucceeded = true;
        } catch (Exception e) {
            log.error("Unexpected error while processing user created event", e);
            processSucceeded = false;
        } finally {
            if (processSucceeded) {
                ack.acknowledge();
                log.info("User created event message acknowledged");
            } else {
                log.warn("User created event message not acknowledged, will be redelivered by Kafka");
            }
        }
    }

    @KafkaListener(topics = "user-deleted", groupId = "auth-service")
    public void consumeUserDeletedEvent(byte[] event, Acknowledgment ack){
        boolean processSucceeded = false;

        try {
            UserDeletedEvent userDeletedEvent = UserDeletedEvent.parseFrom(event);
            String userId = userDeletedEvent.getUserId();

            log.info("UserEventConsumer(User_Deleted): Received user deleted event for user ID {}",
                    userId);

            userCredentialService.deleteById(userId);

            processSucceeded = true;
            log.info("Successfully deleted user credential {}", userId);

        } catch (InvalidProtocolBufferException e) {
            log.error("Deserialization error for user created event", e);
            // Deserialization errors are non-recoverable, so we should acknowledge
            processSucceeded = true;
        } catch (Exception e) {
            log.error("Unexpected error while processing user deleted event", e);
            processSucceeded = false;
        } finally {
            if (processSucceeded) {
                ack.acknowledge();
                log.info("User deleted event message acknowledged");
            } else {
                log.warn("User deleted event message not acknowledged, will be redelivered by Kafka");
            }
        }
    }
}
