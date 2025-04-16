package com.pm.patientservices.mapper;

import com.pm.patientservices.dto.PatientRequestDTO;
import com.pm.patientservices.dto.PatientResposeDTO;
import com.pm.patientservices.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResposeDTO toPatientResposeDTO(Patient patient) {
        PatientResposeDTO patientResposeDTO = new PatientResposeDTO();
        patientResposeDTO.setId(patient.getId().toString());
        patientResposeDTO.setName(patient.getName());
        patientResposeDTO.setEmail(patient.getEmail());
        patientResposeDTO.setAddress(patient.getAddress());
        patientResposeDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        patientResposeDTO.setRegisteredDate(patient.getRegisteredDate().toString());
        return patientResposeDTO;
    }
    public static Patient createPatients(PatientRequestDTO patientRequestDTO) {
       Patient patient = new Patient();
       patient.setName(patientRequestDTO.getName());
       patient.setEmail(patientRequestDTO.getEmail());
       patient.setAddress(patientRequestDTO.getAddress());
       patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
       patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
       return patient;

    }
}
