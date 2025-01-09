package com.bnt.HabitTracker.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.HabitTracker.dto.SpecialHabitDTO;
import com.bnt.HabitTracker.exception.DataIsNotPresentException;
import com.bnt.HabitTracker.exception.DuplicateDataException;
import com.bnt.HabitTracker.exception.IdNotFoundException;
import com.bnt.HabitTracker.exception.ValidationException;
import com.bnt.HabitTracker.model.SpecialHabit;
import com.bnt.HabitTracker.model.Users;
import com.bnt.HabitTracker.repository.SpecialHabitRepository;
import com.bnt.HabitTracker.repository.UserRepository;

@Service
public class SpecialHabitServiceImpl implements SpecialHabitService {

    @Autowired
    private SpecialHabitRepository specialHabitRepository;

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(SpecialHabitServiceImpl.class);

    @Override
    public SpecialHabit saveSpecialHabit(SpecialHabitDTO specialHabitDTO) {
        logger.info("Saving special habit: {}", specialHabitDTO);

        // Validate habit name
        if (specialHabitDTO.getName() == null || specialHabitDTO.getName().trim().isEmpty()) {
            throw new ValidationException("Special habit name is required");
        }
        if (specialHabitRepository.existsByName(specialHabitDTO.getName())) {
        throw new DuplicateDataException("Special habit name already exists: " + specialHabitDTO.getName());
         }

        SpecialHabit specialHabit = new SpecialHabit();
        specialHabit.setName(specialHabitDTO.getName());
        specialHabit.setDescription(specialHabitDTO.getDescription());
        specialHabit.setGoal(specialHabitDTO.getGoal());
        specialHabit.setTarget(specialHabitDTO.getTarget());
        specialHabit.setFrequency(specialHabitDTO.getFrequency());
        specialHabit.setDays(specialHabitDTO.getDays());
        specialHabit.setGoalUnit(specialHabitDTO.getGoalUnit());
        specialHabit.setTargetUnit(specialHabitDTO.getTargetUnit());
        specialHabit.setStartDate(specialHabitDTO.getStartDate());
        specialHabit.setEndDate(specialHabitDTO.getEndDate());
        specialHabit.setTargetTime(specialHabitDTO.getTargetTime()); 
        
        // Handle user association
        Optional<Users> userOptional = userRepository.findById(specialHabitDTO.getUserId());
        if (!userOptional.isPresent()) {
            throw new IdNotFoundException("User not found with ID: " + specialHabitDTO.getUserId());
        }
        specialHabit.setUser(userOptional.get());

        // Save the special habit to the repository
        SpecialHabit savedSpecialHabit = specialHabitRepository.save(specialHabit);
        logger.info("Saved special habit with ID: {}", savedSpecialHabit.getId());
        return savedSpecialHabit;
    }

    @Override
    public Optional<SpecialHabit> getSpecialHabitById(long id) {
        logger.info("Fetching special habit with ID: {}", id);
        Optional<SpecialHabit> optionalSpecialHabit = specialHabitRepository.findById(id);
        if (!optionalSpecialHabit.isPresent()) {
            logger.warn("Special habit not found with ID: {}", id);
            throw new IdNotFoundException("Special habit not found with ID: " + id);
        }
        logger.info("Fetched special habit: {}", optionalSpecialHabit.get());
        return optionalSpecialHabit;
    }

    @Override
public SpecialHabit updateSpecialHabit(SpecialHabit specialHabit) {
    logger.info("Updating special habit with ID: {}", specialHabit.getId());

    Optional<SpecialHabit> optionalSpecialHabit = specialHabitRepository.findById(specialHabit.getId());
    if (!optionalSpecialHabit.isPresent()) {
        logger.warn("Special habit not found with ID: {}", specialHabit.getId());
        throw new IdNotFoundException("Special habit not found with ID: " + specialHabit.getId());
    }

    SpecialHabit existingSpecialHabit = optionalSpecialHabit.get();
    existingSpecialHabit.setName(specialHabit.getName());
    existingSpecialHabit.setDescription(specialHabit.getDescription());
    existingSpecialHabit.setGoal(specialHabit.getGoal());
    existingSpecialHabit.setTarget(specialHabit.getTarget());
    existingSpecialHabit.setFrequency(specialHabit.getFrequency());
    existingSpecialHabit.setStartDate(specialHabit.getStartDate());
    existingSpecialHabit.setEndDate(specialHabit.getEndDate());

    // Update frequency and days based on the new input
    if (specialHabit.getFrequency() == SpecialHabit.Frequency.WEEKLY) {
        existingSpecialHabit.setDays(specialHabit.getDays());
    } else {
        existingSpecialHabit.setDays(new ArrayList<>()); // Clear days if not weekly
    }

    // Handle user association if provided
    if (specialHabit.getUser() != null && specialHabit.getUser().getId() != null) {
        Optional<Users> userOptional = userRepository.findById(specialHabit.getUser().getId());
        if (userOptional.isPresent()) {
            existingSpecialHabit.setUser(userOptional.get());
        } else {
            throw new IdNotFoundException("User not found with ID: " + specialHabit.getUser().getId());
        }
    }

    SpecialHabit updatedSpecialHabit = specialHabitRepository.save(existingSpecialHabit);
    logger.info("Updated special habit with ID: {}", updatedSpecialHabit.getId());
    return updatedSpecialHabit;
}


    @Override
    public List<SpecialHabit> getAllSpecialHabits() {
        logger.info("Fetching all special habits");
        List<SpecialHabit> specialHabits = specialHabitRepository.findAll();
        if (specialHabits.isEmpty()) {
            logger.warn("No special habits found");
            throw new DataIsNotPresentException("No special habits found");
        }
        logger.info("Fetched {} special habits", specialHabits.size());
        return specialHabits;
    }

    @Override
    public void deleteSpecialHabit(long id) {
        logger.info("Deleting special habit with ID: {}", id);
        Optional<SpecialHabit> optionalSpecialHabit = specialHabitRepository.findById(id);
        if (!optionalSpecialHabit.isPresent()) {
            logger.warn("Special habit not found with ID: {}", id);
            throw new IdNotFoundException("Special habit not found with ID: " + id);
        }
        specialHabitRepository.deleteById(id);
        logger.info("Deleted special habit with ID: {}", id);
    }


}
