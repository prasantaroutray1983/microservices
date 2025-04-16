package com.pm.patientservices.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity

 public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Name  is required")
    private String name;

    @NotNull(message = "Email  is required")
    @Email
    @Column(unique = true)
    private String email;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Date of  birth is required")
    private LocalDate dateOfBirth;

    @NotNull(message = "Registered date is required")
    private LocalDate registeredDate;



}
