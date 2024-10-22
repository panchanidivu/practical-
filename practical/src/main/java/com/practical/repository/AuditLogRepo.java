package com.practical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practical.model.AuditLog;

public interface AuditLogRepo extends JpaRepository<AuditLog,Long> {
    
}
