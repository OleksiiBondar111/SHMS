package com.shms.api.service.impl;

import com.shms.api.dao.invoice.InvoiceRepository;
import com.shms.api.dto.appointment.AppointmentDTO;
import com.shms.api.dto.invoice.InvoiceDTO;
import com.shms.api.dto.patient.PatientDTO;
import com.shms.api.exception.ResourceNotFoundException;
import com.shms.api.model.appointemnt.Appointment;
import com.shms.api.model.invoice.Invoice;
import com.shms.api.model.patient.Patient;
import com.shms.api.service.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements EntityService<Invoice, InvoiceDTO> {

    private final InvoiceRepository invoiceRepository;
    private final EntityService<Patient, PatientDTO> patientService;
    private final EntityService<Appointment, AppointmentDTO> appointmentService;

    @Override
    public Invoice create(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice(invoiceDTO);
        initPatientAndAppointment(invoice, invoiceDTO);
        Invoice save = invoiceRepository.save(invoice);
        return save;
    }

    @Override
    public void update(Invoice invoice, InvoiceDTO invoiceDTO) {
        initPatientAndAppointment(invoice, invoiceDTO);
        invoiceRepository.saveAndFlush(invoice);
    }

    @Override
    public Invoice getById(String id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Invoice with id: %s not found", id)));
    }

    private void initPatientAndAppointment(Invoice invoice, InvoiceDTO invoiceDTO) {
        Patient patient = patientService.getById(invoiceDTO.getPatient().getId());
        Appointment appointment = appointmentService.getById(invoiceDTO.getAppointment().getId());
        invoice.setPatient(patient);
        invoice.setAppointment(appointment);
    }

}
