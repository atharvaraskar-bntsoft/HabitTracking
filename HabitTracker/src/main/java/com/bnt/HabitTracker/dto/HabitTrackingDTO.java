package com.bnt.HabitTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HabitTrackingDTO {
    
    private LocalDate date;
    private boolean status;
    private String note;
    

    private Long habitId; 
    private Long userId;   
   // private Long monthId;  
}
