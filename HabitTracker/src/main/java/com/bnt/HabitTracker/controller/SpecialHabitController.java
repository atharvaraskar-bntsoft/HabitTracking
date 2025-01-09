package com.bnt.HabitTracker.controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bnt.HabitTracker.dto.SpecialHabitDTO;
import com.bnt.HabitTracker.model.SpecialHabit;
import com.bnt.HabitTracker.service.SpecialHabitService;

@RestController
@RequestMapping("/habittrackerapi/specialhabit")
public class SpecialHabitController {

    @Autowired
    private SpecialHabitService specialHabitService;

    private final Logger logger = LoggerFactory.getLogger(SpecialHabitController.class);

    @PostMapping
    public ResponseEntity<SpecialHabit> createSpecialHabit(@RequestBody SpecialHabitDTO specialHabitDTO) {
        logger.info("Creating new special habit with data: {}", specialHabitDTO);
        SpecialHabit createdSpecialHabit = specialHabitService.saveSpecialHabit(specialHabitDTO);
        logger.info("Created special habit with ID: {}", createdSpecialHabit.getId());
        return new ResponseEntity<>(createdSpecialHabit, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SpecialHabit>> getAllSpecialHabits() {
        logger.info("Fetching all special habits");
        List<SpecialHabit> listOfAllSpecialHabits = specialHabitService.getAllSpecialHabits();
        logger.info("Fetched {} special habits", listOfAllSpecialHabits.size());
        return new ResponseEntity<>(listOfAllSpecialHabits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<SpecialHabit>> getSpecialHabitById(@PathVariable("id") long id) {
        logger.info("Fetching special habit with ID: {}", id);
        Optional<SpecialHabit> optionalSpecialHabit = specialHabitService.getSpecialHabitById(id);
        if (!optionalSpecialHabit.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Fetched special habit: {}", optionalSpecialHabit.get());
        return new ResponseEntity<>(optionalSpecialHabit, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SpecialHabit> updateSpecialHabit(@RequestBody SpecialHabit specialHabit) {
        logger.info("Updating special habit with ID: {}", specialHabit.getId());
        SpecialHabit updatedSpecialHabit = specialHabitService.updateSpecialHabit(specialHabit);
        logger.info("Updated special habit: {}", updatedSpecialHabit);
        return new ResponseEntity<>(updatedSpecialHabit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpecialHabit(@PathVariable("id") long id) {
        logger.info("Deleting special habit with ID: {}", id);
        specialHabitService.deleteSpecialHabit(id);
        logger.info("Deleted special habit with ID: {}", id);
        return new ResponseEntity<>("Special Habit Deleted Successfully", HttpStatus.OK);
    }
}
