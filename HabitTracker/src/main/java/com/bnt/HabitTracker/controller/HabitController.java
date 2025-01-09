package com.bnt.HabitTracker.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.HabitTracker.dto.HabitDTO;
import com.bnt.HabitTracker.model.Habit;
import com.bnt.HabitTracker.service.HabitService;

@RestController
@RequestMapping("/habittrackerapi/habit")
public class HabitController {

    @Autowired
    private HabitService habitService;
    
    private final Logger logger = LoggerFactory.getLogger(HabitController.class);

    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody HabitDTO habitDTO) {
        logger.info("Creating new habit with data: {}", habitDTO);
        Habit createdHabit = habitService.saveHabit(habitDTO);
        logger.info("Created habit with ID: {}", createdHabit.getId());
        return new ResponseEntity<>(createdHabit, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits() {
        logger.info("Fetching all habits");
        List<Habit> listOfAllHabits = habitService.getAllHabits();
        logger.info("Fetched {} habits", listOfAllHabits.size());
        return new ResponseEntity<>(listOfAllHabits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Habit>> getHabitById(@PathVariable("id") long id) {
        logger.info("Fetching habit with ID: {}", id);
        Optional<Habit> optionalHabit = habitService.getHabitById(id);
        logger.info("Fetched habit: {}", optionalHabit.get());
        return new ResponseEntity<>(optionalHabit, HttpStatus.OK);
        
    }

    @PutMapping
    public ResponseEntity<Habit> updateHabit(@RequestBody Habit habit) {
        logger.info("Updating habit with ID: {}", habit.getId());
        Habit updatedHabit = habitService.updateHabit(habit);
        logger.info("Updated habit: {}", updatedHabit);
        return new ResponseEntity<>(updatedHabit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHabit(@PathVariable("id") long id) {
        logger.info("Deleting habit with ID: {}", id);
        habitService.deleteHabit(id);
        logger.info("Deleted habit with ID: {}", id);
        return new ResponseEntity<>("Habit Deleted Successfully", HttpStatus.OK);
    }

}
