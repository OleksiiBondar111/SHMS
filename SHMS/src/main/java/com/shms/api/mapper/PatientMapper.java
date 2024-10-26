package com.shms.api.mapper;

import com.shms.api.dto.patient.PatientDTO;
import com.shms.api.model.patient.Patient;
import org.mapstruct.Mapper;

@Mapper
public interface PatientMapper extends EntityMapper<Patient, PatientDTO> {

}
