package com.shms.api.mapper;

import com.shms.api.dto.patient.PatientDTO;
import com.shms.api.model.patient.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PatientMapper {
    @Mapping(source = "insurance", target = "insurance")
    PatientDTO map(Patient patient);

    List<PatientDTO> map(List<Patient> patients);

    @Mapping(source = "insurance", target = "insurance")
    Patient map(PatientDTO patientDTO);

}
