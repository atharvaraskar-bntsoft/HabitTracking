package com.bnt.HabitTracker.service;

import java.util.List;
import java.util.Optional;

import com.bnt.HabitTracker.dto.HabitTrackingDTO;
import com.bnt.HabitTracker.model.HabitTracking;

public interface HabitTrackingService {

    HabitTracking saveHabitTracking(HabitTrackingDTO habitTrackingDTO);

    Optional<HabitTracking> getHabitTrackingById(long id);

    HabitTracking updateHabitTracking(HabitTracking habitTracking);

    List<HabitTracking> getAllHabitTrackings();

    void deleteHabitTracking(long id);
}
