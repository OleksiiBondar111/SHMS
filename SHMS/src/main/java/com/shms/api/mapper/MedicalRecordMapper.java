package com.shms.api.mapper;

import com.shms.api.dto.medicalRecord.MedicalRecordDTO;
import com.shms.api.model.medicalRecord.MedicalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MedicalRecordMapper {
    @Mapping(source = "doctor", target = "doctor")
    @Mapping(source = "patient", target = "patient")
    MedicalRecordDTO map(MedicalRecord medicalRecord);

    List<MedicalRecordDTO> map(List<MedicalRecord> medicalRecords);

    @Mapping(source = "doctor", target = "doctor")
    @Mapping(source = "patient", target = "patient")
    MedicalRecord map(MedicalRecordDTO medicalRecordDTO);

}
