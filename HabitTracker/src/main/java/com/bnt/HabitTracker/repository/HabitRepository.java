package com.bnt.HabitTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.HabitTracker.model.Habit;

@Repository
public interface HabitRepository extends JpaRepository<Habit,Long> {
            public Optional<Habit> findByName (String name);
}
