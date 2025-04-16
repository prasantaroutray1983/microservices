package com.pm.patientservices.service;

import com.pm.patientservices.dto.PatientRequestDTO;
import com.pm.patientservices.dto.PatientResposeDTO;
import com.pm.patientservices.exception.EmailAlreadyExistsException;
import com.pm.patientservices.exception.PatientNotFoundException;
import com.pm.patientservices.mapper.PatientMapper;
import com.pm.patientservices.model.Patient;
import com.pm.patientservices.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResposeDTO> getAllPatients() {
        List<Patient> patientList=patientRepository.findAll();
        return patientList.stream().map(PatientMapper::toPatientResposeDTO).toList();
    }

    public PatientResposeDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email already exists " +patientRequestDTO.getEmail());
        }
        Patient newPatient=patientRepository.save(PatientMapper.createPatients(patientRequestDTO));
        return PatientMapper.toPatientResposeDTO(newPatient);

    }

    public PatientResposeDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient=patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient not found: " +id));
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email already exists " +patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setEmail(patientRequestDTO.getEmail());
        Patient updatedPatient=patientRepository.save(patient);
        return PatientMapper.toPatientResposeDTO(updatedPatient);
    }

    public PatientResposeDTO findById(UUID id) {
        Patient patient=patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient not found: " +id));
        patient.getRegisteredDate();
        patient.getId();
        patient.getEmail();
        patient.getAddress();
        patient.getDateOfBirth();

        return PatientMapper.toPatientResposeDTO(patient);

    }
    public boolean deleteByPatientById(UUID id) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            patientRepository.deleteById(id);
            return true; // Patient was found and deleted
        } else {
            return false; // Patient not found
        }
    }
}
