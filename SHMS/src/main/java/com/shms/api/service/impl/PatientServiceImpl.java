package com.shms.api.service.impl;


import com.shms.api.dao.patient.PatientRepository;
import com.shms.api.dto.patient.PatientDTO;
import com.shms.api.exception.ResourceNotFoundException;
import com.shms.api.model.insurance.Insurance;
import com.shms.api.model.patient.Patient;
import com.shms.api.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient create(PatientDTO patientDTO) {
        Patient patient = new Patient(patientDTO);
        Patient save = patientRepository.save(patient);
        return save;
    }

    @Override
    public void update(Patient patient, PatientDTO patientDTO) {
        patient.setDob(patientDTO.getDob());
        patient.setImageId(patientDTO.getImageId());
        patient.setEmail(patientDTO.getEmail());
        patient.setAddress(patientDTO.getAddress());
        patient.setGender(patientDTO.getGender());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setPhone(patientDTO.getPhone());
        patient.setStatus(patientDTO.getStatus());
        patient.setInsurance(new Insurance(patientDTO.getInsurance())); 
        patientRepository.saveAndFlush(patient);
    }

    @Override
    public Patient getById(String id) {
        return patientRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

}
