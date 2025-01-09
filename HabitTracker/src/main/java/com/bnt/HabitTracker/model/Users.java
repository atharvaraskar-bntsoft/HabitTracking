package com.bnt.HabitTracker.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  

    private String username;
    private String password;
  
    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-habit")
    @ToString.Exclude
    private List<Habit> habits =new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference (value = "user-tracking")
    @ToString.Exclude
    private List<HabitTracking> habitTrackings  =new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-specialHabit")
    @ToString.Exclude
    private List<SpecialHabit> specialHabits = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-specialHabitProgress")
    @ToString.Exclude
    private List<SpecialHabitProgress> specialHabitProgressRecords = new ArrayList<>();

}
