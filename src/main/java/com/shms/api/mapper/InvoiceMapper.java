package com.shms.api.mapper;

import com.shms.api.dto.invoice.InvoiceDTO;
import com.shms.api.model.invoice.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface InvoiceMapper extends EntityMapper<Invoice, InvoiceDTO> {

}
