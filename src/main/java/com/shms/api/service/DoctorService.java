package com.shms.api.service;


import com.shms.api.dto.doctor.DoctorDTO;
import com.shms.api.model.doctor.Doctor;

public interface DoctorService {
    Doctor create(DoctorDTO patientDTO);

    void update(Doctor patient, DoctorDTO patientDTO);

    Doctor getById(String id);

}
