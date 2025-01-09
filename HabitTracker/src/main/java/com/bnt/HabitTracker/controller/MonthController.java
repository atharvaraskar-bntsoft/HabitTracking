package com.bnt.HabitTracker.controller;

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

import com.bnt.HabitTracker.model.Month;
import com.bnt.HabitTracker.service.MonthService;
import java.util.*;

@RestController
@RequestMapping("/habittrackerapi/month")
public class MonthController {

     @Autowired
     MonthService monthService;
      
    Logger logger=LoggerFactory.getLogger(MonthController.class);

    @PostMapping
    public ResponseEntity<Month> createMonth(@RequestBody Month month) {
        logger.info("Creating new month with data: {}", month);
        Month createdMonth = monthService.saveMonth(month);
        logger.info("Created month with ID: {}", createdMonth.getId());
        return new ResponseEntity<>(createdMonth, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Month>> getAllMonths() {
        logger.info("Fetching all months");
        List<Month> listOfAllMonths = monthService.getAllMonths();
        logger.info("Fetched {} months", listOfAllMonths.size());
        return new ResponseEntity<>(listOfAllMonths, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Month>> getMonthById(@PathVariable("id") long id) {
        logger.info("Fetching month with ID: {}", id);
        Optional<Month> optionalMonth = monthService.getMonthById(id);
        if(optionalMonth.isPresent()) {
            logger.info("Fetched month: {}", optionalMonth.get());
            return new ResponseEntity<>(optionalMonth, HttpStatus.OK);
        } else {
            logger.warn("Month with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Month> updateMonth(@RequestBody Month month) {
        logger.info("Updating month with ID: {}", month.getId());
        Month updatedMonth = monthService.updateMonth(month);
        logger.info("Updated month: {}", updatedMonth);
        return new ResponseEntity<>(updatedMonth, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMonth(@PathVariable("id") long id) {
        logger.info("Deleting month with ID: {}", id);
        monthService.deleteMonth(id);
        logger.info("Deleted month with ID: {}", id);
        return new ResponseEntity<>("Month Deleted Successfully", HttpStatus.OK);
    }

      

}
