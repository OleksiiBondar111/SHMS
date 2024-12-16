package com.shms.api.mapper;

import com.shms.api.dto.medicalRecord.MedicalRecordDTO;
import com.shms.api.model.medicalRecord.MedicalRecord;
import org.mapstruct.Mapper;

@Mapper
public interface MedicalRecordMapper extends EntityMapper<MedicalRecord, MedicalRecordDTO> {

}
