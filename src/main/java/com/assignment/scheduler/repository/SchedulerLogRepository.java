package com.assignment.scheduler.repository;

import com.assignment.scheduler.model.SchedulerLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerLogRepository extends JpaRepository<SchedulerLog,Integer> {
}
