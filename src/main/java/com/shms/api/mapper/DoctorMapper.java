package com.shms.api.mapper;

import com.shms.api.dto.doctor.DoctorDTO;
import com.shms.api.model.doctor.Doctor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DoctorMapper extends EntityMapper<Doctor, DoctorDTO> {

}
