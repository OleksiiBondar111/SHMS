package com.shms.api.dto.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shms.api.dto.EntityDTO;
import com.shms.api.dto.invoice.InvoiceDTO;
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
public class PaymentDTO extends EntityDTO {

    @NotNull(message = "Invoice Id cannot be null")
    private InvoiceDTO invoice;

    private String paymentMethod;
    
    private BigDecimal paymentAmount;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    protected Date paymentDate;

}
