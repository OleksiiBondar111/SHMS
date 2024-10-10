package com.shms.api.controller;


import com.shms.api.dao.patient.PatientRepository;
import com.shms.api.dto.patient.PatientDTO;
import com.shms.api.mapper.PatientMapper;
import com.shms.api.model.patient.Patient;
import com.shms.api.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/patient")
@Tag(name = "Patient")
public class PatientController {

    private final PatientMapper patientMapper;
    private final PatientService patientService;
    private final PatientRepository patientRepository;

    @Operation(summary = "Create a new patient", description = "Creates a new patient in the system and returns the created patient object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient created"),
            @ApiResponse(responseCode = "400", description = "Request validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO create(@Parameter(description = "Details of the patient to be created", required = true)
                             @RequestBody @Valid PatientDTO patientDTO) {
        return patientMapper.map(patientService.create(patientDTO));
    }

    @Operation(
            summary = "Updates patient",
            description = "Updates patient by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public void update(@Parameter(description = "ID of the patient to be updated", required = true)
                       @PathVariable("id") String id, @RequestBody @Valid PatientDTO patientDTO) {
        Patient patient = patientService.getById(id);
        patientService.update(patient, patientDTO);
    }

    @Operation(
            summary = "Deletes patient",
            description = "Deletes patient by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID of the patient to be deleted", required = true)
                       @PathVariable("id") String id) {
        patientRepository.deleteById(id);
    }

    @Operation(
            summary = "Get all patients",  
            description = "Returns all available active patients. If there are no active patient an empty array returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patients found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<PatientDTO> getAll() {
        return patientMapper.map(patientRepository.findAllByOrderByCreatedAtAsc());
    }

}
