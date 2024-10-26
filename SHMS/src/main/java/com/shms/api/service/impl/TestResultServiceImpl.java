package com.shms.api.service.impl;

import com.shms.api.dao.testResults.TestResultRepository;
import com.shms.api.dto.medicalRecord.MedicalRecordDTO;
import com.shms.api.dto.testResults.TestResultDTO;
import com.shms.api.exception.ResourceNotFoundException;
import com.shms.api.model.medicalRecord.MedicalRecord;
import com.shms.api.model.testResult.TestResult;
import com.shms.api.service.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestResultServiceImpl implements EntityService<TestResult,TestResultDTO> {

    private final TestResultRepository testResultRepository;
    private final EntityService<MedicalRecord, MedicalRecordDTO> medicalRecordService;

    @Override
    public TestResult create(TestResultDTO testResultDTO) {
        TestResult testResult = new TestResult(testResultDTO);
        initMedicalRecord(testResult, testResultDTO);
        TestResult save = testResultRepository.save(testResult);
        return save;
    }

    @Override
    public void update(TestResult testResult, TestResultDTO testResultDTO) {
        initMedicalRecord(testResult, testResultDTO);
        testResultRepository.saveAndFlush(testResult);
    }

    @Override
    public TestResult getById(String id) {
        return testResultRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("TestResult with id: %s not found", id)));
    }

    private void initMedicalRecord(TestResult testResult, TestResultDTO testResultDTO) {
        MedicalRecord medicalRecord = medicalRecordService.getById(testResultDTO.getMedicalRecord().getId());
        testResult.setMedicalRecord(medicalRecord);
    }

}
