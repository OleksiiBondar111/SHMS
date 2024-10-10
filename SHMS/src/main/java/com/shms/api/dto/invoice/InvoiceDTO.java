package com.shms.api.dto.invoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shms.api.dto.EntityDTO;
import com.shms.api.dto.appointment.AppointmentDTO;
import com.shms.api.dto.patient.PatientDTO;
import com.shms.api.enums.InvoiceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class InvoiceDTO extends EntityDTO {

    @NotNull(message = "Patient Id cannot be null")
    private PatientDTO patient;

    @NotNull(message = "Appointment Id cannot be null")
    private AppointmentDTO appointment;

    private BigDecimal totalAmount;

    private InvoiceStatus status;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    protected Date issueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    protected Date dueDate;

}
