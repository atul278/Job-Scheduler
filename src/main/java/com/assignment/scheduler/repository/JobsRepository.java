package com.assignment.scheduler.repository;

import com.assignment.scheduler.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Integer> {
    Optional<Jobs> findByTitle(String title);
}
