package com.shms.api.controller;


import com.shms.api.dao.appointment.AppointmentRepository;
import com.shms.api.dto.appointment.AppointmentDTO;
import com.shms.api.mapper.AppointmentMapper;
import com.shms.api.mapper.EntityMapper;
import com.shms.api.model.appointemnt.Appointment;
import com.shms.api.service.AppointmentService;
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
@RequestMapping("/api/appointment")
@Tag(name = "Appointment")
public class AppointmentController {

    private final AppointmentMapper appointmentMapper;
    private final EntityService<Appointment, AppointmentDTO> appointmentService;
    private final AppointmentRepository appointmentRepository;

    @Operation(summary = "Create a new appointment", description = "Creates a new appointment in the system and returns the created appointment object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Appointment created"),
            @ApiResponse(responseCode = "400", description = "Request validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDTO create(@Parameter(description = "Details of the appointment to be created", required = true)
                                 @RequestBody @Valid AppointmentDTO appointmentDTO) {
        return appointmentMapper.toDto(appointmentService.create(appointmentDTO));
    }

    @Operation(
            summary = "Updates appointment",
            description = "Updates appointment by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public void update(@Parameter(description = "ID of the appointment to be updated", required = true)
                       @PathVariable("id") String id, @RequestBody @Valid AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentService.getById(id);
        appointmentService.update(appointment, appointmentDTO);
    }

    @Operation(
            summary = "Deletes appointment",
            description = "Deletes appointment by provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID of the appointment to be deleted", required = true)
                       @PathVariable("id") String id) {
        appointmentRepository.deleteById(id);
    }

    @Operation(
            summary = "Get all appointments",
            description = "Returns all available active appointments. If there are no active appointment an empty array returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<AppointmentDTO> getAll() {
        return appointmentMapper.map(appointmentRepository.findAllByOrderByCreatedAtAsc());
    }

    @Operation(
            summary = "Get appointment by Id",
            description = "Returns available active appointment by Id. If there are no active then error returns."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found"),
            @ApiResponse(responseCode = "400", description = "Appointment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public AppointmentDTO getById(@PathVariable("id") String id) {
        return appointmentMapper.toDto(appointmentService.getById(id));
    }

}
