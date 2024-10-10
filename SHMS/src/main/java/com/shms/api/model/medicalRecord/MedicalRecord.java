package com.shms.api.model.medicalRecord;

import com.shms.api.dto.medicalRecord.MedicalRecordDTO;
import com.shms.api.model.TrackedEntity;
import com.shms.api.model.doctor.Doctor;
import com.shms.api.model.patient.Patient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "medical_records")
@Data
@NoArgsConstructor
public class MedicalRecord extends TrackedEntity {

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "diagnosis")
    String diagnosis;

    @Column(name = "treatment_plan")
    String treatmentPlan;

    @Column(name = "prescription")
    String prescription;

    public MedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        this.id = medicalRecordDTO.getId();
        this.treatmentPlan = medicalRecordDTO.getTreatmentPlan();
        this.diagnosis = medicalRecordDTO.getDiagnosis();
        this.prescription = medicalRecordDTO.getPrescription();
        if (medicalRecordDTO.getPatient() != null) {
            this.patient = new Patient(medicalRecordDTO.getPatient());
        }
        if (medicalRecordDTO.getDoctor() != null) {
            this.doctor = new Doctor(medicalRecordDTO.getDoctor());
        }
    }

}
