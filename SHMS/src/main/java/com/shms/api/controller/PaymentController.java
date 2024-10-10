package com.shms.api.controller;


import com.shms.api.dao.payment.PaymentRepository;
import com.shms.api.dto.payment.PaymentDTO;
import com.shms.api.mapper.PaymentMapper;
import com.shms.api.model.payment.Payment;
import com.shms.api.service.PaymentService;
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
@RequestMapping("/api/payment")
@Tag(name = "Payment")
public class PaymentController {

    private final PaymentMapper paymentMapper;
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    @Operation(summary = "Create a new payment", description = "Creates a new payment in the system and returns the created payment object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment created"),
            @ApiResponse(responseCode = "400", description = "Request validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDTO create(@Parameter(description = "Details of the payment to be created", required = true)
                                 @RequestBody @Valid PaymentDTO paymentDTO) {
        return paymentMapper.map(paymentService.create(paymentDTO));
    }

    @Operation(
            summary = "Updates payment",
            description = "Updates payment by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public void update(@Parameter(description = "ID of the payment to be updated", required = true)
                       @PathVariable("id") String id, @RequestBody @Valid PaymentDTO paymentDTO) {
        Payment payment = paymentService.getById(id);
        paymentService.update(payment, paymentDTO);
    }

    @Operation(
            summary = "Deletes payment",
            description = "Deletes payment by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID of the payment to be deleted", required = true)
                       @PathVariable("id") String id) {
        paymentRepository.deleteById(id);
    }

    @Operation(
            summary = "Get all medicalRecords",
            description = "Returns all available active medicalRecords. If there are no active payment an empty array returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payments found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<PaymentDTO> getAll() {
        return paymentMapper.map(paymentRepository.findAllByOrderByCreatedAtAsc());
    }

    @Operation(
            summary = "Get payment by Id",
            description = "Returns available active payment by Id. If there are no active then error returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payments found"),
            @ApiResponse(responseCode = "400", description = "Payment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public PaymentDTO getById(@PathVariable("id") String id) {
        return paymentMapper.map(paymentService.getById(id));
    }

}
