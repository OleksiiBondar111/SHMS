package com.shms.api.service.impl;


import com.shms.api.dao.doctor.DoctorRepository;
import com.shms.api.dao.medicalRecord.MedicalRecordRepository;
import com.shms.api.dao.patient.PatientRepository;
import com.shms.api.dto.medicalRecord.MedicalRecordDTO;
import com.shms.api.exception.ResourceNotFoundException;
import com.shms.api.model.doctor.Doctor;
import com.shms.api.model.medicalRecord.MedicalRecord;
import com.shms.api.model.patient.Patient;
import com.shms.api.service.EntityService;
import com.shms.api.service.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements EntityService<MedicalRecord, MedicalRecordDTO> {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public MedicalRecord create(MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = new MedicalRecord(medicalRecordDTO);
        initPatientAndDoctorIfPresent(medicalRecord, medicalRecordDTO);
        MedicalRecord save = medicalRecordRepository.save(medicalRecord);
        return save;
    }

    @Override
    public void update(MedicalRecord medicalRecord, MedicalRecordDTO medicalRecordDTO) {
        medicalRecord.setPatient(new Patient(medicalRecordDTO.getPatient()));
        medicalRecord.setDoctor(new Doctor(medicalRecordDTO.getDoctor()));
        medicalRecord.setDiagnosis(medicalRecordDTO.getDiagnosis());
        medicalRecord.setPrescription(medicalRecordDTO.getPrescription());
        medicalRecord.setTreatmentPlan(medicalRecordDTO.getTreatmentPlan());
        initPatientAndDoctorIfPresent(medicalRecord, medicalRecordDTO);
        medicalRecordRepository.saveAndFlush(medicalRecord);
    }

    @Override
    public MedicalRecord getById(String id) {
        return medicalRecordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("MedicalRecord with id: %s not found", id)));
    }

    private void initPatientAndDoctorIfPresent(MedicalRecord medicalRecord, MedicalRecordDTO medicalRecordDTO) {
        Optional<Patient> patient = patientRepository.findById(medicalRecordDTO.getPatient().getId());
        Optional<Doctor> doctor = doctorRepository.findById(medicalRecordDTO.getDoctor().getId());
        patient.ifPresent(medicalRecord::setPatient);
        doctor.ifPresent(medicalRecord::setDoctor);
    }

}
