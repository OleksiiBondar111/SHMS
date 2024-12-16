package com.shms.api.controller;


import com.shms.api.dao.insurance.InsuranceRepository;
import com.shms.api.dto.insurance.InsuranceDTO;
import com.shms.api.mapper.InsuranceMapper;
import com.shms.api.model.insurance.Insurance;
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
@RequestMapping("/api/insurance")
@Tag(name = "Insurance")
public class InsuranceController {

    private final InsuranceMapper insuranceMapper;
    private final EntityService<Insurance, InsuranceDTO> insuranceService;
    private final InsuranceRepository insuranceRepository;

    @Operation(summary = "Create a new insurance", description = "Creates a new insurance in the system and returns the created insurance object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Insurance created"),
            @ApiResponse(responseCode = "400", description = "Request validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceDTO create(@Parameter(description = "Details of the insurance to be created", required = true)
                               @RequestBody @Valid InsuranceDTO insuranceDTO) {

        return insuranceMapper.toDto(insuranceService.create(insuranceDTO));
    }

    @Operation(
            summary = "Updates insurance",
            description = "Updates insurance by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insurance updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public void update(@Parameter(description = "ID of the insurance to be updated", required = true)
                       @PathVariable("id") String id, @RequestBody @Valid InsuranceDTO insuranceDTO) {
        Insurance insurance = insuranceService.getById(id);
        insuranceService.update(insurance, insuranceDTO);
    }

    @Operation(
            summary = "Deletes insurance",
            description = "Deletes insurance by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insurances updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID of the insurance to be deleted", required = true)
                       @PathVariable("id") String id) {
        insuranceRepository.deleteById(id);
    }

    @Operation(
            summary = "Get all insurances",  // Short description
            description = "Returns all available active insurances. If there are no active insurance an empty array returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insurances found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<InsuranceDTO> getAll() {
        return insuranceMapper.map(insuranceRepository.findAllByOrderByCreatedAtAsc());
    }

}
