package com.shms.api.model.invoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shms.api.dto.invoice.InvoiceDTO;
import com.shms.api.enums.InvoiceStatus;
import com.shms.api.model.TrackedEntity;
import com.shms.api.model.appointemnt.Appointment;
import com.shms.api.model.patient.Patient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
public class Invoice extends TrackedEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "issue_date")
    @CreationTimestamp
    private Date issueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "due_date")
    @CreationTimestamp
    private Date dueDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public Invoice(InvoiceDTO invoiceDTO) {
        this.id = invoiceDTO.getId();
        this.issueDate = invoiceDTO.getIssueDate();
        this.dueDate = invoiceDTO.getDueDate();
        this.totalAmount = invoiceDTO.getTotalAmount();
        if (invoiceDTO.getPatient() != null) {
            this.patient = new Patient(invoiceDTO.getPatient());
        }
        if (invoiceDTO.getAppointment() != null) {
            this.appointment = new Appointment(invoiceDTO.getAppointment());
        }
        this.status = invoiceDTO.getStatus();

    }

}
