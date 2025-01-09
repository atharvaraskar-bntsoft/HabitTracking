package com.bnt.HabitTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.HabitTracker.model.Month;

@Repository
public interface MonthRepository extends JpaRepository<Month,Long>{

}
