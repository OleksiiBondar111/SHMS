package com.shms.api.mapper;

import com.shms.api.dto.payment.PaymentDTO;
import com.shms.api.model.payment.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PaymentMapper {
    @Mapping(source = "invoice", target = "invoice")
    PaymentDTO map(Payment payment);

    List<PaymentDTO> map(List<Payment> medicalRecords);

    @Mapping(source = "invoice", target = "invoice")
    Payment map(PaymentDTO medicalRecordDTO);

}
