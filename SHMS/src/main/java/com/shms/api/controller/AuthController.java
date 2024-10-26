package com.shms.api.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Appointment")
public class AuthController {
    
    class AuthObject{
        String access_token;

        public AuthObject(String access_token) {
            this.access_token = access_token;
        }
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
    @GetMapping
    public AuthObject getToken() {
        return new AuthObject("Test_123");
    }

}
