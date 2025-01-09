package com.bnt.HabitTracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.HabitTracker.dto.HabitDTO;
import com.bnt.HabitTracker.exception.DataIsNotPresentException;
import com.bnt.HabitTracker.exception.IdNotFoundException;
import com.bnt.HabitTracker.exception.ValidationException;
import com.bnt.HabitTracker.model.Habit;
import com.bnt.HabitTracker.model.Users;
import com.bnt.HabitTracker.repository.HabitRepository;
import com.bnt.HabitTracker.repository.UserRepository;

@Service
public class HabitServiceImpl implements HabitService {

   @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(HabitServiceImpl.class);

    @Override
    public Habit saveHabit(HabitDTO habitDTO) {
        logger.info("Saving habit: {}", habitDTO);

        // Validate habit name
        if (habitDTO.getName() == null || habitDTO.getName().trim().isEmpty()) {
            throw new ValidationException("Habit name is required");
        }

        Habit habit = new Habit();
        habit.setName(habitDTO.getName());
        habit.setDescription(habitDTO.getDescription());
        habit.setGoal(habitDTO.getGoal());
        habit.setUnit(habitDTO.getUnit()); 
        habit.setFrequency(habitDTO.getFrequency());
        habit.setStartDate(habitDTO.getStartDate());
        habit.setEndDate(habitDTO.getEndDate());


        Optional<Users> userOptional = userRepository.findById(habitDTO.getUserId());
        if (!userOptional.isPresent()) {
            throw new IdNotFoundException("User not found with ID: " + habitDTO.getUserId());
        }
        habit.setUser(userOptional.get());

        // Save the habit to the repository
        Habit savedHabit = habitRepository.save(habit);
        logger.info("Saved habit with ID: {}", savedHabit.getId());
        return savedHabit;
    }

    @Override
    public Optional<Habit> getHabitById(long id) {
        logger.info("Fetching habit with ID: {}", id);
        Optional<Habit> optionalHabit = habitRepository.findById(id);
        if (!optionalHabit.isPresent()) {
            logger.warn("Habit not found with ID: {}", id);
            throw new IdNotFoundException("Habit not found with ID: " + id);
        }
        logger.info("Fetched habit: {}", optionalHabit.get());
        return optionalHabit;
    }

    @Override
    public Habit updateHabit(Habit habit) {
        logger.info("Updating habit with ID: {}", habit.getId());

        Optional<Habit> optionalHabit = habitRepository.findById(habit.getId());
        if (!optionalHabit.isPresent()) {
            logger.warn("Habit not found with ID: {}", habit.getId());
            throw new IdNotFoundException("Habit not found with ID: " + habit.getId());
        }

        Habit existingHabit = optionalHabit.get();
        existingHabit.setName(habit.getName());
        existingHabit.setDescription(habit.getDescription());
        existingHabit.setGoal(habit.getGoal());
        existingHabit.setUnit(habit.getUnit());  // 
        existingHabit.setFrequency(habit.getFrequency());
        existingHabit.setStartDate(habit.getStartDate());
        existingHabit.setEndDate(habit.getEndDate());

        Habit updatedHabit = habitRepository.save(existingHabit);
        logger.info("Updated habit with ID: {}", updatedHabit.getId());
        return updatedHabit;
    }

    @Override
    public List<Habit> getAllHabits() {
        logger.info("Fetching all habits");
        List<Habit> habits = habitRepository.findAll();
        if (habits.isEmpty()) {
            logger.warn("No habits found");
            throw new DataIsNotPresentException("No habits found");
        }
        logger.info("Fetched {} habits", habits.size());
        return habits;
    }

    @Override
    public void deleteHabit(long id) {
        logger.info("Deleting habit with ID: {}", id);
        Optional<Habit> optionalHabit = habitRepository.findById(id);
        if (!optionalHabit.isPresent()) {
            logger.warn("Habit not found with ID: {}", id);
            throw new IdNotFoundException("Habit not found with ID: " + id);
        }
        logger.info("Deleted habit with ID: {}", id);
        habitRepository.deleteById(id);
    }

}
