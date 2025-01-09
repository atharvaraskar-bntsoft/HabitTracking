package com.bnt.HabitTracker.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "habit_tracking")
public class HabitTracking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate date;
    private boolean status;
    private String note;
    
  
    @ManyToOne
    @JoinColumn(name = "habit_id")
    @JsonBackReference(value = "habit-tracking")
    @ToString.Exclude
    private Habit habit;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonBackReference (value = "user-tracking")
    private Users user;

    // @ManyToOne
    // @JoinColumn(name = "month_id")
    // @JsonBackReference(value = "month-tracking")
    // @ToString.Exclude
    // private Month month;
}
