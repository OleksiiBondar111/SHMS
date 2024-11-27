package com.shms.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shms.api.auth.TokenRepository;
import com.shms.api.configuration.JwtAuthenticationFilter;
import com.shms.api.configuration.JwtService;
import com.shms.api.dao.insurance.InsuranceRepository;
import com.shms.api.dto.insurance.InsuranceDTO;
import com.shms.api.mapper.InsuranceMapper;
import com.shms.api.model.insurance.Insurance;
import com.shms.api.service.EntityService;
import com.shms.api.service.InsuranceService;
import com.shms.api.testBase.InsuranceTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(InsuranceController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class InsuranceControllerTest extends InsuranceTestBase {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InsuranceMapper mapper;

    @MockBean
    private EntityService<Insurance, InsuranceDTO> insuranceService;

    @MockBean
    private InsuranceRepository insuranceRepository;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    JwtService jwtService;
    
    @MockBean
    TokenRepository tokenRepository;

    @Test
    public void shouldReturnInsuranceList() throws Exception {
        when(insuranceRepository.findAllByOrderByCreatedAtAsc()).thenReturn(List.of(insurance));
        when(mapper.map(List.of(insurance))).thenReturn(List.of(insuranceDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/insurance"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("Id"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].providerName").value("ProviderName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].coverageDetails").value("CoverageDetails"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].policyNumber").value("PolicyNumber"));
    }

    @Test
    public void shouldCreateInsurance() throws Exception {
        when(insuranceService.create(insuranceDTO)).thenReturn(insurance);
        when(mapper.toDto(insurance)).thenReturn(insuranceDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/insurance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(insuranceDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("Id"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.providerName").value("ProviderName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.coverageDetails").value("CoverageDetails"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.policyNumber").value("PolicyNumber"));
    }

    @Test
    public void shouldUpdateInsurance() throws Exception {
        when(insuranceService.getById(anyString())).thenReturn(insurance);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/insurance/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(insuranceDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldDeleteInsurance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/insurance/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(insuranceDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldRaiseValidationException() throws Exception {
        insuranceDTO.setPolicyNumber(null);
        insuranceDTO.setProviderName(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/insurance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(insuranceDTO)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(containsString("Policy number cannot be null")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(containsString("Provider name cannot be null")));
    }
}
