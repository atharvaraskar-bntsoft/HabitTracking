package com.bnt.HabitTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.HabitTracker.model.SpecialHabitProgress;

@Repository
public interface SpecialHabitProgressRepository extends JpaRepository<SpecialHabitProgress, Long> {
}
