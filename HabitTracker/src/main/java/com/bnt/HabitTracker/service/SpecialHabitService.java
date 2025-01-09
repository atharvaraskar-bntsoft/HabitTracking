package com.bnt.HabitTracker.service;

import java.util.List;
import java.util.Optional;

import com.bnt.HabitTracker.dto.SpecialHabitDTO;
import com.bnt.HabitTracker.model.SpecialHabit;

public interface SpecialHabitService {

    public SpecialHabit saveSpecialHabit(SpecialHabitDTO specialHabitDTO);

    public Optional<SpecialHabit> getSpecialHabitById(long id);

    public SpecialHabit updateSpecialHabit(SpecialHabit specialHabit);

    public List<SpecialHabit> getAllSpecialHabits();

    public void deleteSpecialHabit(long id);
}
