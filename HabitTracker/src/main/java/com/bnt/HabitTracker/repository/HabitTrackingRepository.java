package com.bnt.HabitTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.HabitTracker.model.HabitTracking;

@Repository
public interface HabitTrackingRepository extends JpaRepository<HabitTracking, Long> {
    
}
