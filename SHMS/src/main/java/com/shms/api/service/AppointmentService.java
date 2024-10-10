package com.shms.api.service;

import com.shms.api.dto.appointment.AppointmentDTO;
import com.shms.api.model.appointemnt.Appointment;

public interface AppointmentService {
    Appointment create(AppointmentDTO appointmentDTO);

    void update(Appointment appointment, AppointmentDTO appointmentDTO);

    Appointment getById(String id);

}
