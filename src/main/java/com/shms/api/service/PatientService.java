package com.shms.api.service;

import com.shms.api.dto.patient.PatientDTO;
import com.shms.api.model.patient.Patient;

public interface PatientService {
    Patient create(PatientDTO patientDTO);

    void update(Patient patient, PatientDTO patientDTO);

    Patient getById(String id);

}
