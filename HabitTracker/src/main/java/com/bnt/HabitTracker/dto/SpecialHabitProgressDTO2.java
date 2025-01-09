package com.bnt.HabitTracker.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialHabitProgressDTO2 {

    private Long id; // Ensure this field exists
    private Long specialHabitId;
    private Long userId;
    private LocalDate date;
    private int progress;
    private boolean completed;
    private String note;
}

