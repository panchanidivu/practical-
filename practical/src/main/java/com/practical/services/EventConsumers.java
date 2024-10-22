package com.practical.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.practical.model.AuditLog;
import com.practical.repository.AuditLogRepo;

@Service
public class EventConsumers {

    @Autowired
    private AuditLogRepo auditLogRepo;


    @KafkaListener(topics = "user-create-topic", groupId = "audit")
    public void handleUserCreateEvent(String message) {
        saveAuditLog("CREATE", message);
    }

    @KafkaListener(topics = "user-update-topic", groupId = "audit")
    public void handleUserUpdateEvent(String message) {
        saveAuditLog("UPDATE", message);
    }

    @KafkaListener(topics = "user-delete-topic", groupId = "audit")
    public void handleUserDeleteEvent(String message) {
        saveAuditLog("DELETE", message);
    }

    private void saveAuditLog(String eventType, String details) {
        AuditLog log = new AuditLog();
        log.setEventType(eventType);
        log.setEntityId(extractEntityId(details));  // Assuming message contains the ID
        log.setDetails(details);
        log.setTimestamp(new Date());
        auditLogRepo.save(log);
    }

    private String extractEntityId(String details) {
        // Assuming details contain ID in a format you can parse.
        // Example logic: extracting the ID from a comma-separated message string
        return details.split(",")[0];  // Example: ID is the first field
    }
    
}
