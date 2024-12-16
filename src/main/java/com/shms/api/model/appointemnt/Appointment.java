package com.shms.api.model.appointemnt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shms.api.dto.appointment.AppointmentDTO;
import com.shms.api.enums.Status;
import com.shms.api.model.TrackedEntity;
import com.shms.api.model.doctor.Doctor;
import com.shms.api.model.patient.Patient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
public class Appointment extends TrackedEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "appointment_date")
    @CreationTimestamp
    private Date appointmentDate;

    @Column(name = "reason")
    private String reason;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Appointment(AppointmentDTO appointmentDTO) {
        this.id = appointmentDTO.getId();
        this.appointmentDate = appointmentDTO.getAppointmentDate();
        if (appointmentDTO.getDoctor() != null) {
            this.doctor = new Doctor(appointmentDTO.getDoctor());
        }
        if (appointmentDTO.getPatient() != null) {
            this.patient = new Patient(appointmentDTO.getPatient());
        }
        this.status = appointmentDTO.getStatus();
        this.reason = appointmentDTO.getReason();
    }

}
