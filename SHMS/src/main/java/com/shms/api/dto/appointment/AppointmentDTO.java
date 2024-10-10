package com.shms.api.dto.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shms.api.dto.doctor.DoctorDTO;
import com.shms.api.dto.EntityDTO;
import com.shms.api.dto.patient.PatientDTO;
import com.shms.api.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class AppointmentDTO extends EntityDTO {

    @NotNull(message = "Patient Id cannot be null")
    private PatientDTO patient;

    @NotNull(message = "Doctor Id cannot be null")
    private DoctorDTO doctor;

    @NotNull(message = "Appointment date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date appointmentDate;

    @NotNull(message = "Status cannot be null. Please use Status enum values")
    private Status status;

    private String reason;
    
}
