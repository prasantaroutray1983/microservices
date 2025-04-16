package com.pm.patientservices.dto;

import lombok.Data;

@Data
public class PatientResposeDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String dateOfBirth;
    private String  registeredDate;

}
