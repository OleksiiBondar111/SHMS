package com.shms.api.model.testResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shms.api.dto.testResults.TestResultDTO;
import com.shms.api.model.TrackedEntity;
import com.shms.api.model.medicalRecord.MedicalRecord;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "test_results")
@Data
@NoArgsConstructor
public class TestResult extends TrackedEntity {

    @ManyToOne
    @JoinColumn(name = "record_id")
    private MedicalRecord medicalRecord;

    @Column(name = "test_name")
    String testName;

    @Column(name = "test_type")
    String testType;

    @Column(name = "test_result")
    String testResult;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "test_date")
    @CreationTimestamp
    protected Date testDate;

    public TestResult(TestResultDTO testResultDTO) {
        this.id = testResultDTO.getId();
        this.testDate = testResultDTO.getTestDate();
        this.testName = testResultDTO.getTestName();
        this.testType = testResultDTO.getTestType();
        this.testResult = testResultDTO.getTestResult();
        this.medicalRecord = new MedicalRecord(testResultDTO.getMedicalRecord());
    }

}
