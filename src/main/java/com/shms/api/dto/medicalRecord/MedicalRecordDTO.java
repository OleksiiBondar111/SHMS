package com.shms.api.dto.medicalRecord;

import com.shms.api.dto.EntityDTO;
import com.shms.api.dto.doctor.DoctorDTO;
import com.shms.api.dto.patient.PatientDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class MedicalRecordDTO extends EntityDTO {
    
    @NotNull(message = "Patient Id cannot be null")
    private PatientDTO patient;

    @NotNull(message = "Doctor Id cannot be null")
    private DoctorDTO doctor;

    private String diagnosis;

    private String prescription;
  
    private String treatmentPlan;
    
}
