package com.shms.api.service.impl;


import com.shms.api.dao.doctor.DoctorRepository;
import com.shms.api.dto.doctor.DoctorDTO;
import com.shms.api.exception.ResourceNotFoundException;
import com.shms.api.model.doctor.Doctor;
import com.shms.api.service.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements EntityService<Doctor,DoctorDTO> {

    private final DoctorRepository doctorRepository;

    @Override
    public Doctor create(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor(doctorDTO);
        Doctor save = doctorRepository.save(doctor);
        return save;
    }

    @Override
    public void update(Doctor doctor, DoctorDTO doctorDTO) {
        doctor.setAvailability(doctorDTO.getAvailability());
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setPhone(doctorDTO.getPhone());
        doctor.setOfficeAddress(doctorDTO.getOfficeAddress());
        doctor.setImageId(doctorDTO.getImageId());
        doctor.setStatus(doctorDTO.getStatus());
        doctor.setSpecialty(doctorDTO.getSpecialty());
        doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public Doctor getById(String id) {
        return doctorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

}
