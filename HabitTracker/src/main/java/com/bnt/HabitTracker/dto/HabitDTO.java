package com.bnt.HabitTracker.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HabitDTO {

    private String name;
    private Long userId;       // ID of the associated user
    private String description; // Description of the habit
    private int goal;              // The goal quantity
    private String unit;    
    private String frequency;  // Frequency of the habit (e.g., daily, weekly, monthly)
    private LocalDate startDate; // Changed to LocalDate
    private LocalDate endDate;   // Changed to LocalDate
}


