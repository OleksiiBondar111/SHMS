package com.shms.api.service;

import com.shms.api.dto.invoice.InvoiceDTO;
import com.shms.api.model.invoice.Invoice;

public interface InvoiceService {
    Invoice create(InvoiceDTO invoiceDTO);

    void update(Invoice invoice, InvoiceDTO invoiceDTO);

    Invoice getById(String id);

}
