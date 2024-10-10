package com.shms.api.service;

import com.shms.api.dto.medicalRecord.MedicalRecordDTO;
import com.shms.api.model.medicalRecord.MedicalRecord;

public interface MedicalRecordService {
    MedicalRecord create(MedicalRecordDTO medicalRecordDTO);

    void update(MedicalRecord medicalRecord, MedicalRecordDTO medicalRecordDTO);

    MedicalRecord getById(String id);

}
