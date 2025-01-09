package com.bnt.HabitTracker.service;

import java.util.List;
import java.util.Optional;

import com.bnt.HabitTracker.dto.SpecialHabitProgressDTO;
import com.bnt.HabitTracker.dto.SpecialHabitProgressDTO2;
import com.bnt.HabitTracker.model.SpecialHabitProgress;

public interface SpecialHabitProgressService {

    SpecialHabitProgress saveSpecialHabitProgress(SpecialHabitProgressDTO specialHabitProgressDTO);

    Optional<SpecialHabitProgress> getSpecialHabitProgressById(long id);

    // SpecialHabitProgress updateSpecialHabitProgress(SpecialHabitProgress specialHabitProgress);
     
public SpecialHabitProgress updateSpecialHabitProgress(SpecialHabitProgressDTO2 specialHabitProgressDTO2);
   
    List<SpecialHabitProgress> getAllSpecialHabitProgress();

    void deleteSpecialHabitProgress(long id);

}
