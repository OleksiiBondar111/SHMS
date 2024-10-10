package com.shms.api.service.impl;

import com.shms.api.dao.payment.PaymentRepository;
import com.shms.api.dto.payment.PaymentDTO;
import com.shms.api.exception.ResourceNotFoundException;
import com.shms.api.model.invoice.Invoice;
import com.shms.api.model.payment.Payment;
import com.shms.api.service.InvoiceService;
import com.shms.api.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceService invoiceService;

    @Override
    public Payment create(PaymentDTO paymentDTO) {
        Payment payment = new Payment(paymentDTO);
        initPatientAndAppointment(payment, paymentDTO);
        Payment save = paymentRepository.save(payment);
        return save;
    }

    @Override
    public void update(Payment payment, PaymentDTO paymentDTO) {
        initPatientAndAppointment(payment, paymentDTO);
        paymentRepository.saveAndFlush(payment);
    }

    @Override
    public Payment getById(String id) {
        return paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Payment with id: %s not found", id)));
    }

    private void initPatientAndAppointment(Payment payment, PaymentDTO paymentDTO) {
        Invoice invoice = invoiceService.getById(paymentDTO.getInvoice().getId());
        payment.setInvoice(invoice);
    }

}
