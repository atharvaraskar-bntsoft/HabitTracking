package com.bnt.HabitTracker.model;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@NoArgsConstructor
public class SpecialHabit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int target;

    public enum Frequency {
        DAILY,
        WEEKLY,
        MONTHLY
    }

    public enum DaysOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    private int goal;
    private String goalUnit;  
    private String targetUnit;

    @Enumerated(EnumType.STRING)
    private Frequency frequency; 

    @ElementCollection(targetClass = DaysOfWeek.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "habit_weekly_days", joinColumns = @JoinColumn(name = "habit_id"))
    @Column(name = "day_of_week")
    private List<DaysOfWeek> days = new ArrayList<>(); 


    private LocalTime targetTime;  
    private LocalDate startDate;  
    private LocalDate endDate;    

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "user-specialHabit")
    @ToString.Exclude
    private Users user;

    @OneToMany(mappedBy = "specialHabit", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "specialHabit-specialHabitProgress")
    @ToString.Exclude
    private List<SpecialHabitProgress> progressRecords = new ArrayList<>();
}
