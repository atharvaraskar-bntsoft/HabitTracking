package com.bnt.HabitTracker.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.HabitTracker.dto.HabitTrackingDTO;
import com.bnt.HabitTracker.exception.DataIsNotPresentException;
import com.bnt.HabitTracker.exception.IdNotFoundException;
import com.bnt.HabitTracker.exception.ValidationException;
import com.bnt.HabitTracker.model.HabitTracking;
import com.bnt.HabitTracker.model.Habit;

import com.bnt.HabitTracker.model.Users;
import com.bnt.HabitTracker.repository.HabitTrackingRepository;
import com.bnt.HabitTracker.repository.HabitRepository;

import com.bnt.HabitTracker.repository.UserRepository;

@Service
public class HabitTrackingServiceImpl implements HabitTrackingService {

    @Autowired
    private HabitTrackingRepository habitTrackingRepository;

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private MonthRepository monthRepository;

    private final Logger logger = LoggerFactory.getLogger(HabitTrackingServiceImpl.class);

    @Override
    public HabitTracking saveHabitTracking(HabitTrackingDTO habitTrackingDTO) {
        logger.info("Saving habit tracking record: {}", habitTrackingDTO);

        if (habitTrackingDTO.getHabitId() == null) {
            throw new ValidationException("Habit ID is required");
        }
        
        HabitTracking habitTracking = new HabitTracking();
        habitTracking.setDate(habitTrackingDTO.getDate());
        habitTracking.setStatus(habitTrackingDTO.isStatus());
        habitTracking.setNote(habitTrackingDTO.getNote());
      
        

        Optional<Habit> habitOptional = habitRepository.findById(habitTrackingDTO.getHabitId());
        if (!habitOptional.isPresent()) {
            throw new IdNotFoundException("Habit not found with ID: " + habitTrackingDTO.getHabitId());
        }
        habitTracking.setHabit(habitOptional.get());

        Optional<Users> userOptional = userRepository.findById(habitTrackingDTO.getUserId());
        if (!userOptional.isPresent()) {
            throw new IdNotFoundException("User not found with ID: " + habitTrackingDTO.getUserId());
        }
        habitTracking.setUser(userOptional.get());

        // Optional<Month> monthOptional = monthRepository.findById(habitTrackingDTO.getMonthId());
        // if (!monthOptional.isPresent()) {
        //     throw new IdNotFoundException("Month not found with ID: " + habitTrackingDTO.getMonthId());
        // }
        // habitTracking.setMonth(monthOptional.get());

        HabitTracking savedHabitTracking = habitTrackingRepository.save(habitTracking);
        logger.info("Saved habit tracking record with ID: {}", savedHabitTracking.getId());
        return savedHabitTracking;
    }

    @Override
    public Optional<HabitTracking> getHabitTrackingById(long id) {
        logger.info("Fetching habit tracking record with ID: {}", id);
        Optional<HabitTracking> optionalHabitTracking = habitTrackingRepository.findById(id);
        if (!optionalHabitTracking.isPresent()) {
            logger.warn("Habit tracking record not found with ID: {}", id);
            throw new IdNotFoundException("Habit tracking record not found with ID: " + id);
        }
        logger.info("Fetched habit tracking record: {}", optionalHabitTracking.get());
        return optionalHabitTracking;
    }

    @Override
    public HabitTracking updateHabitTracking(HabitTracking habitTracking) {
        logger.info("Updating habit tracking record with ID: {}", habitTracking.getId());
        Optional<HabitTracking> optionalHabitTracking = habitTrackingRepository.findById(habitTracking.getId());
        if (!optionalHabitTracking.isPresent()) {
            logger.warn("Habit tracking record not found with ID: {}", habitTracking.getId());
            throw new IdNotFoundException("Habit tracking record not found with ID: " + habitTracking.getId());
        }
        HabitTracking updatedHabitTracking = habitTrackingRepository.save(habitTracking);
        logger.info("Updated habit tracking record: {}", updatedHabitTracking);
        return updatedHabitTracking;
    }

    @Override
    public List<HabitTracking> getAllHabitTrackings() {
        logger.info("Fetching all habit tracking records");
        List<HabitTracking> habitTrackings = habitTrackingRepository.findAll();
        if (habitTrackings.isEmpty()) {
            logger.warn("No habit tracking records found");
            throw new DataIsNotPresentException("No habit tracking records found");
        }
        logger.info("Fetched {} habit tracking records", habitTrackings.size());
        return habitTrackings;
    }

    @Override
    public void deleteHabitTracking(long id) {
        logger.info("Deleting habit tracking record with ID: {}", id);
        Optional<HabitTracking> optionalHabitTracking = habitTrackingRepository.findById(id);
        if (!optionalHabitTracking.isPresent()) {
            logger.warn("Habit tracking record not found with ID: {}", id);
            throw new IdNotFoundException("Habit tracking record not found with ID: " + id);
        }
        habitTrackingRepository.deleteById(id);
        logger.info("Deleted habit tracking record with ID: {}", id);
    }
}
