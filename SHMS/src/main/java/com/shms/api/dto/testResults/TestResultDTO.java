package com.shms.api.dto.testResults;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shms.api.dto.EntityDTO;
import com.shms.api.dto.medicalRecord.MedicalRecordDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class TestResultDTO extends EntityDTO {

    @NotNull(message = "Medical record Id cannot be null")
    private MedicalRecordDTO medicalRecord;

    private String testName;

    private String testType;

    private String testResult;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    protected Date testDate;

}
