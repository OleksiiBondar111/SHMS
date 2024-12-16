package com.shms.api.mapper;

import com.shms.api.dto.payment.PaymentDTO;
import com.shms.api.model.payment.Payment;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentMapper extends EntityMapper<Payment, PaymentDTO> {

}
