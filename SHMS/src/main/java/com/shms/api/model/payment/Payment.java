package com.shms.api.model.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shms.api.dto.payment.PaymentDTO;
import com.shms.api.model.TrackedEntity;
import com.shms.api.model.invoice.Invoice;
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
@Table(name = "payments")
@Data
@NoArgsConstructor
public class Payment extends TrackedEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "payment_date")
    @CreationTimestamp
    private Date paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public Payment(PaymentDTO paymentDTO) {
        this.id = paymentDTO.getId();
        this.paymentAmount = paymentDTO.getPaymentAmount();
        this.paymentMethod = paymentDTO.getPaymentMethod();
        this.paymentDate = paymentDTO.getPaymentDate();
        if (paymentDTO.getInvoice() != null) {
            this.invoice = new Invoice(paymentDTO.getInvoice());
        }
    }

}
