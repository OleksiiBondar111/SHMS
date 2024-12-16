package com.shms.api.controller;


import com.shms.api.dao.invoice.InvoiceRepository;
import com.shms.api.dto.invoice.InvoiceDTO;
import com.shms.api.mapper.InvoiceMapper;
import com.shms.api.model.invoice.Invoice;
import com.shms.api.service.EntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/invoice")
@Tag(name = "Invoice")
public class InvoiceController {

    private final InvoiceMapper invoiceMapper;
    private final EntityService<Invoice, InvoiceDTO> invoiceService;
    private final InvoiceRepository invoiceRepository;

    @Operation(summary = "Create a new invoice", description = "Creates a new invoice in the system and returns the created invoice object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Invoice created"),
            @ApiResponse(responseCode = "400", description = "Request validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceDTO create(@Parameter(description = "Details of the invoice to be created", required = true)
                             @RequestBody @Valid InvoiceDTO invoiceDTO) {
        return invoiceMapper.toDto(invoiceService.create(invoiceDTO));
    }

    @Operation(
            summary = "Updates invoice",
            description = "Updates invoice by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public void update(@Parameter(description = "ID of the invoice to be updated", required = true)
                       @PathVariable("id") String id, @RequestBody @Valid InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceService.getById(id);
        invoiceService.update(invoice, invoiceDTO);
    }

    @Operation(
            summary = "Deletes invoice",
            description = "Deletes invoice by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID of the invoice to be deleted", required = true)
                       @PathVariable("id") String id) {
        invoiceRepository.deleteById(id);
    }

    @Operation(
            summary = "Get all medicalRecords",
            description = "Returns all available active medicalRecords. If there are no active invoice an empty array returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoices found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<InvoiceDTO> getAll() {
        return invoiceMapper.map(invoiceRepository.findAllByOrderByCreatedAtAsc());
    }

    @Operation(
            summary = "Get invoice by Id",
            description = "Returns available active invoice by Id. If there are no active then error returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoices found"),
            @ApiResponse(responseCode = "400", description = "Invoice not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public InvoiceDTO getById(@PathVariable("id") String id) {
        return invoiceMapper.toDto(invoiceService.getById(id));
    }

}
