package com.shms.api.service;

import com.shms.api.dto.testResults.TestResultDTO;
import com.shms.api.model.testResult.TestResult;

public interface TestResultService {
    TestResult create(TestResultDTO patientDTO);

    void update(TestResult testResult, TestResultDTO patientDTO);

    TestResult getById(String id);

}
