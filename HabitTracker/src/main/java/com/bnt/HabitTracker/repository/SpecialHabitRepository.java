package com.bnt.HabitTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.HabitTracker.model.SpecialHabit;

@Repository
public interface SpecialHabitRepository extends JpaRepository<SpecialHabit, Long> {

    boolean existsByName(String name);
}