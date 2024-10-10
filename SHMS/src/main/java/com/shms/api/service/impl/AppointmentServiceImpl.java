package com.shms.api.service.impl;

import com.shms.api.dao.appointment.AppointmentRepository;
import com.shms.api.dto.appointment.AppointmentDTO;
import com.shms.api.exception.ResourceNotFoundException;
import com.shms.api.model.appointemnt.Appointment;
import com.shms.api.model.doctor.Doctor;
import com.shms.api.model.patient.Patient;
import com.shms.api.service.AppointmentService;
import com.shms.api.service.DoctorService;
import com.shms.api.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Override
    public Appointment create(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment(appointmentDTO);
        initPatientAndDoctor(appointment, appointmentDTO);
        Appointment save = appointmentRepository.save(appointment);
        return save;
    }

    @Override
    public void update(Appointment appointment, AppointmentDTO appointmentDTO) {
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setReason(appointmentDTO.getReason());
        appointment.setPatient(new Patient(appointmentDTO.getPatient()));
        appointment.setDoctor(new Doctor(appointmentDTO.getDoctor()));
        initPatientAndDoctor(appointment, appointmentDTO);
        appointmentRepository.saveAndFlush(appointment);
    }

    @Override
    public Appointment getById(String id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Appointment with id: %s not found", id)));
    }

    private void initPatientAndDoctor(Appointment appointment, AppointmentDTO appointmentDTO) {
        Patient patient = patientService.getById(appointmentDTO.getPatient().getId());
        Doctor doctor = doctorService.getById(appointmentDTO.getDoctor().getId());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
    }

}
