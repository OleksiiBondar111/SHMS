package com.shms.api.mapper;

import com.shms.api.dto.appointment.AppointmentDTO;
import com.shms.api.model.appointemnt.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    @Mapping(source = "doctor", target = "doctor")
    @Mapping(source = "patient", target = "patient")
    AppointmentDTO map(Appointment appointment);

    List<AppointmentDTO> map(List<Appointment> appointments);

    @Mapping(source = "doctor", target = "doctor")
    @Mapping(source = "patient", target = "patient")
    Appointment map(AppointmentDTO appointmentDTO);

}
