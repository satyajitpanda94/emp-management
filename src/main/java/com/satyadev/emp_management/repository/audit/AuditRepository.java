package com.satyadev.emp_management.repository.audit;

import com.satyadev.emp_management.entity.audit.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditLog, Long> {
}
