package com.shms.api.mapper;

import com.shms.api.dto.invoice.InvoiceDTO;
import com.shms.api.model.invoice.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface InvoiceMapper {
    @Mapping(source = "patient", target = "patient")
    @Mapping(source = "appointment", target = "appointment")
    InvoiceDTO map(Invoice invoice);

    List<InvoiceDTO> map(List<Invoice> medicalRecords);

    @Mapping(source = "patient", target = "patient")
    @Mapping(source = "appointment", target = "appointment")
    Invoice map(InvoiceDTO medicalRecordDTO);

}
