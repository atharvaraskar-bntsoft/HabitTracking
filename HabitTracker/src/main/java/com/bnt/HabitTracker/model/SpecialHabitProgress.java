package com.bnt.HabitTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SpecialHabitProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;  
    private String note;
    private int progress;  
    private boolean isCompleted;
    private LocalTime time;   

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "user-specialHabitProgress")
    @ToString.Exclude
    private Users user;

    @ManyToOne
    @JoinColumn(name = "special_habit_id", nullable = false)
    @JsonBackReference(value = "specialHabit-specialHabitProgress")
    @ToString.Exclude
    private SpecialHabit specialHabit;

   

    
}

