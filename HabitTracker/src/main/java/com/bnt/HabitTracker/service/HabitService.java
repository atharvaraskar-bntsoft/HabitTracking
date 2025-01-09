package com.bnt.HabitTracker.service;

import java.util.List;
import java.util.Optional;

import com.bnt.HabitTracker.dto.HabitDTO;
import com.bnt.HabitTracker.model.Habit;

public interface HabitService {

     public Habit saveHabit(HabitDTO habitDTO);

     public Optional<Habit> getHabitById(long id);

     public Habit updateHabit(Habit habit);

     public List<Habit>  getAllHabits();

     public void  deleteHabit(long id);
}
