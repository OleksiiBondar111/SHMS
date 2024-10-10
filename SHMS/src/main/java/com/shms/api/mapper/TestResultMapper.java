package com.shms.api.mapper;

import com.shms.api.dto.testResults.TestResultDTO;
import com.shms.api.model.testResult.TestResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TestResultMapper {
    TestResultDTO map(TestResult testResult);

    List<TestResultDTO> map(List<TestResult> insurances);
}
