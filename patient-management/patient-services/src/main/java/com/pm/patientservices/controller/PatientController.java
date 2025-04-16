package com.pm.patientservices.controller;

import com.pm.patientservices.dto.PatientRequestDTO;
import com.pm.patientservices.dto.PatientResposeDTO;
import com.pm.patientservices.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/patients")
@Tag(name="Patient",description = "API for patient management")
public class PatientController {


    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping
    @Operation(summary ="Get patient")
    public ResponseEntity<List<PatientResposeDTO>> getAllPatients(){
        List<PatientResposeDTO> patientResposeDTOList=patientService.getAllPatients();
        return ResponseEntity.ok().body(patientResposeDTOList);
    }
    @PostMapping
    @Operation(summary ="Create a new patient")
    public ResponseEntity<PatientResposeDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResposeDTO responseDTO=patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
    @PutMapping("/{id}")
    @Operation(summary ="Update a patient")
    public ResponseEntity<PatientResposeDTO> updatePatient(@PathVariable UUID id, @Valid @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResposeDTO responseDTO=patientService.updatePatient(id,patientRequestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("/{id}")
    @Operation(summary ="Get a patient")
    public ResponseEntity<PatientResposeDTO> getPatientById(@PathVariable UUID id){
        PatientResposeDTO responseDTO=patientService.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }


    @DeleteMapping("/{id}")
    @Operation(summary ="Delete a patient")
    public ResponseEntity<Void> deleteByPatientById(@PathVariable UUID id) {
        boolean isDeleted = patientService.deleteByPatientById(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found if the patient ID doesn't exist
        }
    }
}
