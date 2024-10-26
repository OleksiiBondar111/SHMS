package com.shms.api.mapper;

import com.shms.api.dto.testResults.TestResultDTO;
import com.shms.api.model.testResult.TestResult;
import org.mapstruct.Mapper;

@Mapper
public interface TestResultMapper extends EntityMapper<TestResult, TestResultDTO> {

}
