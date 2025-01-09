package com.bnt.HabitTracker.controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bnt.HabitTracker.dto.HabitTrackingDTO;
import com.bnt.HabitTracker.model.HabitTracking;
import com.bnt.HabitTracker.service.HabitTrackingService;

@RestController
@RequestMapping("/habittrackerapi/habittracking")
public class HabitTrackingController {

    @Autowired
    private HabitTrackingService habitTrackingService;
    
    private final Logger logger = LoggerFactory.getLogger(HabitTrackingController.class);

    @PostMapping
    public ResponseEntity<HabitTracking> createHabitTracking(@RequestBody HabitTrackingDTO habitTrackingDTO) {
        logger.info("Creating new habit tracking record with data: {}", habitTrackingDTO);
        HabitTracking createdHabitTracking = habitTrackingService.saveHabitTracking(habitTrackingDTO);
        logger.info("Created habit tracking record with ID: {}", createdHabitTracking.getId());
        return new ResponseEntity<>(createdHabitTracking, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HabitTracking>> getAllHabitTrackings() {
        logger.info("Fetching all habit tracking records");
        List<HabitTracking> listOfAllHabitTrackings = habitTrackingService.getAllHabitTrackings();
        logger.info("Fetched {} habit tracking records", listOfAllHabitTrackings.size());
        return new ResponseEntity<>(listOfAllHabitTrackings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<HabitTracking>> getHabitTrackingById(@PathVariable("id") long id) {
        logger.info("Fetching habit tracking record with ID: {}", id);
        Optional<HabitTracking> optionalHabitTracking = habitTrackingService.getHabitTrackingById(id);
        if (optionalHabitTracking.isPresent()) {
            logger.info("Fetched habit tracking record: {}", optionalHabitTracking.get());
            return new ResponseEntity<>(optionalHabitTracking, HttpStatus.OK);
        } else {
            logger.warn("Habit tracking record with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<HabitTracking> updateHabitTracking(@RequestBody HabitTracking habitTracking) {
        logger.info("Updating habit tracking record with ID: {}", habitTracking.getId());
        HabitTracking updatedHabitTracking = habitTrackingService.updateHabitTracking(habitTracking);
        logger.info("Updated habit tracking record: {}", updatedHabitTracking);
        return new ResponseEntity<>(updatedHabitTracking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHabitTracking(@PathVariable("id") long id) {
        logger.info("Deleting habit tracking record with ID: {}", id);
        habitTrackingService.deleteHabitTracking(id);
        logger.info("Deleted habit tracking record with ID: {}", id);
        return new ResponseEntity<>("Habit Tracking Record Deleted Successfully", HttpStatus.OK);
    }
}

