package com.shms.api.controller;



import com.shms.api.dao.testResults.TestResultRepository;
import com.shms.api.dto.testResults.TestResultDTO;
import com.shms.api.mapper.TestResultMapper;
import com.shms.api.model.testResult.TestResult;
import com.shms.api.service.TestResultService;
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
@RequestMapping("/api/testResult")
@Tag(name = "Test Result")
public class TestResultsController {

    private final TestResultMapper testResultMapper;
    private final TestResultService testResultService;
    private final TestResultRepository testResultRepository;

    @Operation(summary = "Create a new testResult", description = "Creates a new testResult in the system and returns the created testResult object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TestResult created"),
            @ApiResponse(responseCode = "400", description = "Request validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestResultDTO create(@Parameter(description = "Details of the testResult to be created", required = true)
                                 @RequestBody @Valid TestResultDTO testResultDTO) {
        return testResultMapper.map(testResultService.create(testResultDTO));
    }

    @Operation(
            summary = "Updates testResult",
            description = "Updates testResult by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TestResult updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public void update(@Parameter(description = "ID of the testResult to be updated", required = true)
                       @PathVariable("id") String id, @RequestBody @Valid TestResultDTO testResultDTO) {
        TestResult testResult = testResultService.getById(id);
        testResultService.update(testResult, testResultDTO);
    }

    @Operation(
            summary = "Deletes testResult",
            description = "Deletes testResult by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TestResult deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID of the testResult to be deleted", required = true)
                       @PathVariable("id") String id) {
        testResultRepository.deleteById(id);
    }

    @Operation(
            summary = "Get all testResults",
            description = "Returns all available active testResults. If there are no active testResult an empty array returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalRecords found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<TestResultDTO> getAll() {
        return testResultMapper.map(testResultRepository.findAllByOrderByCreatedAtAsc());
    }

    @Operation(
            summary = "Get testResult by Id",
            description = "Returns available active testResult by Id. If there are no active then error returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "MedicalRecords found"),
            @ApiResponse(responseCode = "400", description = "TestResult not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public TestResultDTO getById(@PathVariable("id") String id) {
        return testResultMapper.map(testResultService.getById(id));
    }

}
