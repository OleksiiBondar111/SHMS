package com.shms.api.mapper;

import com.shms.api.dto.doctor.DoctorDTO;
import com.shms.api.model.doctor.Doctor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DoctorMapper {

    DoctorDTO map(Doctor doctor);

    List<DoctorDTO> map(List<Doctor> doctors);

}
