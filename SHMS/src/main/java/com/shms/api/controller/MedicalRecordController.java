package com.shms.api.controller;


import com.shms.api.dao.medicalRecord.MedicalRecordRepository;
import com.shms.api.dto.medicalRecord.MedicalRecordDTO;
import com.shms.api.mapper.MedicalRecordMapper;
import com.shms.api.model.medicalRecord.MedicalRecord;
import com.shms.api.service.EntityService;
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
@RequestMapping("/api/medicalRecord")
@Tag(name = "Medical Record")
public class MedicalRecordController {

    private final MedicalRecordMapper medicalRecordMapper;
    private final EntityService<MedicalRecord, MedicalRecordDTO> medicalRecordService;
    private final MedicalRecordRepository medicalRecordRepository;

    @Operation(summary = "Create a new medicalRecord", description = "Creates a new medicalRecord in the system and returns the created medicalRecord object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "MedicalRecord created"),
            @ApiResponse(responseCode = "400", description = "Request validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalRecordDTO create(@Parameter(description = "Details of the medicalRecord to be created", required = true)
                                   @RequestBody @Valid MedicalRecordDTO medicalRecordDTO) {
        return medicalRecordMapper.toDto(medicalRecordService.create(medicalRecordDTO));
    }

    @Operation(
            summary = "Updates medicalRecord",
            description = "Updates medicalRecord by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalRecord updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public void update(@Parameter(description = "ID of the medicalRecord to be updated", required = true)
                       @PathVariable("id") String id, @RequestBody @Valid MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordService.getById(id);
        medicalRecordService.update(medicalRecord, medicalRecordDTO);
    }

    @Operation(
            summary = "Deletes medicalRecord",
            description = "Deletes medicalRecord by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalRecord deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID of the medicalRecord to be deleted", required = true)
                       @PathVariable("id") String id) {
        medicalRecordRepository.deleteById(id);
    }

    @Operation(
            summary = "Get all medicalRecords",
            description = "Returns all available active medicalRecords. If there are no active medicalRecord an empty array returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalRecords found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<MedicalRecordDTO> getAll() {
        return medicalRecordMapper.map(medicalRecordRepository.findAllByOrderByCreatedAtAsc());
    }

    @Operation(
            summary = "Get medicalRecord by Id",
            description = "Returns available active medicalRecord by Id. If there are no active then error returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalRecords found"),
            @ApiResponse(responseCode = "400", description = "MedicalRecord not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public MedicalRecordDTO getById(@PathVariable("id") String id) {
        return medicalRecordMapper.toDto(medicalRecordService.getById(id));
    }

}
