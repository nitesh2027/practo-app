package com.example.practo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="reviews")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private  String reviewMessage;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "doctor_Id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="patient_Id")
    private Patient patient;
}
