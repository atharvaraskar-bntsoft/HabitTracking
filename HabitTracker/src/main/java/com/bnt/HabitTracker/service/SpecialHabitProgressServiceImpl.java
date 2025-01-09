package com.bnt.HabitTracker.service;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.HabitTracker.dto.SpecialHabitProgressDTO;
import com.bnt.HabitTracker.dto.SpecialHabitProgressDTO2;
import com.bnt.HabitTracker.exception.DataIsNotPresentException;
import com.bnt.HabitTracker.exception.IdNotFoundException;
import com.bnt.HabitTracker.exception.ValidationException;
import com.bnt.HabitTracker.model.SpecialHabit;
import com.bnt.HabitTracker.model.SpecialHabitProgress;
import com.bnt.HabitTracker.model.Users;
import com.bnt.HabitTracker.repository.SpecialHabitProgressRepository;
import com.bnt.HabitTracker.repository.SpecialHabitRepository;
import com.bnt.HabitTracker.repository.UserRepository;

@Service
public class SpecialHabitProgressServiceImpl implements SpecialHabitProgressService {

    private final Logger logger = LoggerFactory.getLogger(SpecialHabitProgressServiceImpl.class);

    @Autowired
    private SpecialHabitProgressRepository specialHabitProgressRepository;

    @Autowired
    private SpecialHabitRepository specialHabitRepository;

     @Autowired
    private UserRepository userRepository;

    @Override
    public SpecialHabitProgress saveSpecialHabitProgress(SpecialHabitProgressDTO specialHabitProgressDTO) {
        logger.info("Saving special habit progress: {}", specialHabitProgressDTO);

        // Validate special habit ID
        if (specialHabitProgressDTO.getSpecialHabitId() == null) {
            throw new ValidationException("Special habit ID is required");
        }

        // Fetch the associated SpecialHabit
        Optional<SpecialHabit> specialHabitOptional = specialHabitRepository.findById(specialHabitProgressDTO.getSpecialHabitId());
        if (!specialHabitOptional.isPresent()) {
            throw new IdNotFoundException("Special habit not found with ID: " + specialHabitProgressDTO.getSpecialHabitId());
        }

        Optional<Users> userOptional = userRepository.findById(specialHabitProgressDTO.getUserId());
        if (!userOptional.isPresent()) {
            throw new IdNotFoundException("User not found with ID: " + specialHabitProgressDTO.getUserId());
        }


        SpecialHabitProgress specialHabitProgress = new SpecialHabitProgress();
        specialHabitProgress.setSpecialHabit(specialHabitOptional.get());
        specialHabitProgress.setDate(specialHabitProgressDTO.getDate());
        specialHabitProgress.setProgress(specialHabitProgressDTO.getProgress());
        specialHabitProgress.setCompleted(specialHabitProgressDTO.getIsCompleted());
        specialHabitProgress.setNote(specialHabitProgressDTO.getNote());
        specialHabitProgress.setUser(userOptional.get());
        specialHabitProgress.setTime(specialHabitProgressDTO.getTime());

        SpecialHabitProgress savedProgress = specialHabitProgressRepository.save(specialHabitProgress);
        logger.info("Saved special habit progress with ID: {}", savedProgress.getId());
        return savedProgress;
    }

    @Override
    public Optional<SpecialHabitProgress> getSpecialHabitProgressById(long id) {
        logger.info("Fetching special habit progress with ID: {}", id);
        Optional<SpecialHabitProgress> optionalProgress = specialHabitProgressRepository.findById(id);
        if (!optionalProgress.isPresent()) {
            logger.warn("Special habit progress not found with ID: {}", id);
            throw new IdNotFoundException("Special habit progress not found with ID: " + id);
        }
        logger.info("Fetched special habit progress: {}", optionalProgress.get());
        return optionalProgress;
    }



//     @Override
// public SpecialHabitProgress updateSpecialHabitProgress(SpecialHabitProgress specialHabitProgress) {
//     logger.info("Updating special habit progress with ID: {}", specialHabitProgress.getId());

//     // Check if the special habit progress exists
//     Optional<SpecialHabitProgress> optionalProgress = specialHabitProgressRepository.findById(specialHabitProgress.getId());
//     if (!optionalProgress.isPresent()) {
//         logger.warn("Special habit progress not found with ID: {}", specialHabitProgress.getId());
//         throw new IdNotFoundException("Special habit progress not found with ID: " + specialHabitProgress.getId());
//     }

//     SpecialHabitProgress existingProgress = optionalProgress.get();

//     // Check if special habit exists and set it
//     if (specialHabitProgress.getSpecialHabit() != null) {
//         Optional<SpecialHabit> specialHabitOptional = specialHabitRepository.findById(specialHabitProgress.getSpecialHabit().getId());
//         if (!specialHabitOptional.isPresent()) {
//             throw new IdNotFoundException("Special habit not found with ID: " + specialHabitProgress.getSpecialHabit().getId());
//         }
//         existingProgress.setSpecialHabit(specialHabitOptional.get());
//     }

//     // Check if user exists and set it
//     if (specialHabitProgress.getUser() != null) {
//         Optional<Users> userOptional = userRepository.findById(specialHabitProgress.getUser().getId());
//         if (!userOptional.isPresent()) {
//             throw new IdNotFoundException("User not found with ID: " + specialHabitProgress.getUser().getId());
//         }
//         existingProgress.setUser(userOptional.get());
//     }

//     // Update other fields
//     existingProgress.setDate(specialHabitProgress.getDate());
//     existingProgress.setProgress(specialHabitProgress.getProgress());
//     existingProgress.setCompleted(specialHabitProgress.isCompleted());
//     existingProgress.setNote(specialHabitProgress.getNote());

//     // Save the updated progress
//     SpecialHabitProgress updatedProgress = specialHabitProgressRepository.save(existingProgress);
//     logger.info("Updated special habit progress with ID: {}", updatedProgress.getId());

//     return updatedProgress;
// }

      

    @Override
public SpecialHabitProgress updateSpecialHabitProgress(SpecialHabitProgressDTO2 specialHabitProgressDTO2) {
    logger.info("Updating special habit progress with ID: {}", specialHabitProgressDTO2.getId());

    // Check if the special habit progress exists
    Optional<SpecialHabitProgress> optionalProgress = specialHabitProgressRepository.findById(specialHabitProgressDTO2.getId());
    if (!optionalProgress.isPresent()) {
        logger.warn("Special habit progress not found with ID: {}", specialHabitProgressDTO2.getId());
        throw new IdNotFoundException("Special habit progress not found with ID: " + specialHabitProgressDTO2.getId());
    }

    SpecialHabitProgress existingProgress = optionalProgress.get();

    // Check if special habit exists and set it
    if (specialHabitProgressDTO2.getSpecialHabitId() != null) {
        Optional<SpecialHabit> specialHabitOptional = specialHabitRepository.findById(specialHabitProgressDTO2.getSpecialHabitId());
        if (!specialHabitOptional.isPresent()) {
            throw new IdNotFoundException("Special habit not found with ID: " + specialHabitProgressDTO2.getSpecialHabitId());
        }
        existingProgress.setSpecialHabit(specialHabitOptional.get());
    }

    // Check if user exists and set it
    if (specialHabitProgressDTO2.getUserId() != null) {
        Optional<Users> userOptional = userRepository.findById(specialHabitProgressDTO2.getUserId());
        if (!userOptional.isPresent()) {
            throw new IdNotFoundException("User not found with ID: " + specialHabitProgressDTO2.getUserId());
        }
        existingProgress.setUser(userOptional.get());
    }

    // Update other fields
    existingProgress.setDate(specialHabitProgressDTO2.getDate());
    existingProgress.setProgress(specialHabitProgressDTO2.getProgress());
    existingProgress.setCompleted(specialHabitProgressDTO2.isCompleted());
    existingProgress.setNote(specialHabitProgressDTO2.getNote());

    // Save the updated progress
    SpecialHabitProgress updatedProgress = specialHabitProgressRepository.save(existingProgress);
    logger.info("Updated special habit progress with ID: {}", updatedProgress.getId());

    return updatedProgress;
}







    @Override
    public List<SpecialHabitProgress> getAllSpecialHabitProgress() {
        logger.info("Fetching all special habit progress records");
        List<SpecialHabitProgress> progressRecords = specialHabitProgressRepository.findAll();
        if (progressRecords.isEmpty()) {
            logger.warn("No special habit progress records found");
            throw new DataIsNotPresentException("No special habit progress records found");
        }
        logger.info("Fetched {} special habit progress records", progressRecords.size());
        return progressRecords;
    }

    @Override
    public void deleteSpecialHabitProgress(long id) {
        logger.info("Deleting special habit progress with ID: {}", id);
        Optional<SpecialHabitProgress> optionalProgress = specialHabitProgressRepository.findById(id);
        if (!optionalProgress.isPresent()) {
            logger.warn("Special habit progress not found with ID: {}", id);
            throw new IdNotFoundException("Special habit progress not found with ID: " + id);
        }
        specialHabitProgressRepository.deleteById(id);
        logger.info("Deleted special habit progress with ID: {}", id);
    }
}
