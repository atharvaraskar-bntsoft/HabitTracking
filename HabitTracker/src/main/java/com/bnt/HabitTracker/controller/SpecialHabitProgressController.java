package com.bnt.HabitTracker.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bnt.HabitTracker.dto.SpecialHabitProgressDTO;
import com.bnt.HabitTracker.dto.SpecialHabitProgressDTO2;
import com.bnt.HabitTracker.exception.IdNotFoundException;
import com.bnt.HabitTracker.model.SpecialHabitProgress;
import com.bnt.HabitTracker.service.SpecialHabitProgressService;

@RestController
@RequestMapping("/habittrackerapi/specialhabitprogress")
public class SpecialHabitProgressController {

    @Autowired
    private SpecialHabitProgressService specialHabitProgressService;

    private final Logger logger = LoggerFactory.getLogger(SpecialHabitProgressController.class);

    @PostMapping
    public ResponseEntity<SpecialHabitProgress> createSpecialHabitProgress(@RequestBody SpecialHabitProgressDTO specialHabitProgressDTO) {
        logger.info("Creating new special habit progress with data: {}", specialHabitProgressDTO);
        SpecialHabitProgress createdProgress = specialHabitProgressService.saveSpecialHabitProgress(specialHabitProgressDTO);
        logger.info("Created special habit progress with ID: {}", createdProgress.getId());
        return new ResponseEntity<>(createdProgress, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SpecialHabitProgress>> getAllSpecialHabitProgress() {
        logger.info("Fetching all special habit progress records");
        List<SpecialHabitProgress> listOfAllProgress = specialHabitProgressService.getAllSpecialHabitProgress();
        logger.info("Fetched {} special habit progress records", listOfAllProgress.size());
        return new ResponseEntity<>(listOfAllProgress, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<SpecialHabitProgress>> getSpecialHabitProgressById(@PathVariable("id") long id) {
        logger.info("Fetching special habit progress with ID: {}", id);
        Optional<SpecialHabitProgress> optionalProgress = specialHabitProgressService.getSpecialHabitProgressById(id);
        if (!optionalProgress.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Fetched special habit progress: {}", optionalProgress.get());
        return new ResponseEntity<>(optionalProgress, HttpStatus.OK);
    }

    // @PutMapping
    // public ResponseEntity<SpecialHabitProgress> updateSpecialHabitProgress(@RequestBody SpecialHabitProgress specialHabitProgress) {
    //     logger.info("Updating special habit progress with ID: {}", specialHabitProgress.getId());
    //     SpecialHabitProgress updatedProgress = specialHabitProgressService.updateSpecialHabitProgress(specialHabitProgress);
    //     logger.info("Updated special habit progress: {}", updatedProgress);
    //     return new ResponseEntity<>(updatedProgress, HttpStatus.OK);
    // }

      @PutMapping("/update")
    public ResponseEntity<SpecialHabitProgress> updateSpecialHabitProgress(
            @RequestBody SpecialHabitProgressDTO2 specialHabitProgressDTO2) {
        try {
            SpecialHabitProgress updatedProgress = specialHabitProgressService.updateSpecialHabitProgress(specialHabitProgressDTO2);
            return ResponseEntity.ok(updatedProgress);
        } catch (IdNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

  

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpecialHabitProgress(@PathVariable("id") long id) {
        logger.info("Deleting special habit progress with ID: {}", id);
        specialHabitProgressService.deleteSpecialHabitProgress(id);
        logger.info("Deleted special habit progress with ID: {}", id);
        return new ResponseEntity<>("Special Habit Progress Deleted Successfully", HttpStatus.OK);
    }
}

