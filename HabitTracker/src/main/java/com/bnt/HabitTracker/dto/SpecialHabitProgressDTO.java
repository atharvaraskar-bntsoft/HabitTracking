package com.bnt.HabitTracker.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialHabitProgressDTO {

    private Long specialHabitId;  // ID of the associated SpecialHabit
    private Long userId;   
    
    private LocalDate date;       // The date when the progress is recorded
    private int progress;         // Progress made on this date
    private boolean isCompleted;  // Whether the habit was completed on this date
    private String note;
    private LocalTime time;
    
    public boolean getIsCompleted() {  // Explicit getter for isCompleted
        return isCompleted;
    }
}
