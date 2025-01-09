package com.bnt.HabitTracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.HabitTracker.exception.DataIsNotPresentException;
import com.bnt.HabitTracker.exception.IdNotFoundException;
import com.bnt.HabitTracker.exception.ValidationException;
import com.bnt.HabitTracker.model.Month;
import com.bnt.HabitTracker.repository.MonthRepository;


@Service
public class MonthServiceImpl implements MonthService {
     
    @Autowired
    private MonthRepository monthRepository;

    private final Logger logger = LoggerFactory.getLogger(MonthServiceImpl.class);

    @Override
    public Month saveMonth(Month month) {
        logger.info("Saving month: {}", month);
            
        if (month.getYear() <= 0) {
            throw new ValidationException("Year must be a positive number");
        }
        Month savedMonth = monthRepository.save(month);
        logger.info("Saved month with ID: {}", savedMonth.getId());
        return savedMonth;
    }

    @Override
    public Optional<Month> getMonthById(long id) {
        logger.info("Fetching month with ID: {}", id);
        Optional<Month> optionalMonth = monthRepository.findById(id);
        if (!optionalMonth.isPresent()) {
            logger.warn("Month not found with ID: {}", id);
            throw new IdNotFoundException("Month not found with ID: " + id);
        }
        logger.info("Fetched month: {}", optionalMonth.get());
        return optionalMonth;
    }

    @Override
    public Month updateMonth(Month month) {
        logger.info("Updating month with ID: {}", month.getId());
        Optional<Month> optionalMonth = monthRepository.findById(month.getId());
        if (!optionalMonth.isPresent()) {
            logger.warn("Month not found with ID: {}", month.getId());
            throw new IdNotFoundException("Month not found with ID: " + month.getId());
        }
        Month updatedMonth = monthRepository.save(month);
        logger.info("Updated month: {}", updatedMonth);
        return updatedMonth;
    }

    @Override
    public List<Month> getAllMonths() {
        logger.info("Fetching all months");
        List<Month> months = monthRepository.findAll();
        if (months.isEmpty()) {
            logger.warn("No months found");
            throw new DataIsNotPresentException("No months found");
        }
        logger.info("Fetched {} months", months.size());
        return months;
    }

    @Override
    public void deleteMonth(long id) {
        logger.info("Deleting month with ID: {}", id);
        Optional<Month> optionalMonth = monthRepository.findById(id);
        if (!optionalMonth.isPresent()) {
            logger.warn("Month not found with ID: {}", id);
            throw new IdNotFoundException("Month not found with ID: " + id);
        }
        monthRepository.deleteById(id);
        logger.info("Deleted month with ID: {}", id);
    }
}
