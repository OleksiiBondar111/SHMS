package com.shms.api.service;

import com.shms.api.dto.payment.PaymentDTO;
import com.shms.api.model.payment.Payment;

public interface PaymentService {
    Payment create(PaymentDTO paymentDTO);

    void update(Payment payment, PaymentDTO paymentDTO);

    Payment getById(String id);

}
