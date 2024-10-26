package com.shms.api.mapper;

import com.shms.api.dto.appointment.AppointmentDTO;
import com.shms.api.model.appointemnt.Appointment;
import org.mapstruct.Mapper;

// MapStruct Can't generate mapping method for a generic type 
@Mapper
public interface AppointmentMapper extends EntityMapper<Appointment, AppointmentDTO> {

}
