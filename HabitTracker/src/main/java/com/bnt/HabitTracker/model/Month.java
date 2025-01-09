package com.bnt.HabitTracker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.*;




@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "month")
public class Month {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 

    private int year;

    


    // @OneToMany(mappedBy = "month", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonManagedReference(value = "month-tracking")
    // @ToString.Exclude
    // private List<HabitTracking> habitTrackers = new ArrayList<>();

    
}



