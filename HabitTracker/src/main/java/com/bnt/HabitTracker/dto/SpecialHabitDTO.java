package com.bnt.HabitTracker.dto;


import com.bnt.HabitTracker.model.SpecialHabit.Frequency;
import com.bnt.HabitTracker.model.SpecialHabit.DaysOfWeek;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialHabitDTO {

    private String name;
    private String description;
    private int goal;
    private int target;
    private Frequency frequency;
    private List<DaysOfWeek> days; 
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
    private String goalUnit;  
    private String targetUnit; 
    private LocalTime targetTime;
}


