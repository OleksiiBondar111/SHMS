package com.shms.api.service;


import com.shms.api.dao.insurance.InsuranceRepository;
import com.shms.api.exception.ResourceNotFoundException;
import com.shms.api.model.insurance.Insurance;
import com.shms.api.service.impl.InsuranceServiceImpl;
import com.shms.api.testBase.InsuranceTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class InsuranceServiceTest extends InsuranceTestBase {

    @Autowired
    private InsuranceServiceImpl insuranceService;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Test
    public void shouldCreteAndFetchInsurance() {
        Insurance saved = insuranceService.create(insuranceDTO);
        Insurance insurance1 = insuranceService.getById(saved.getId());
        assertThat(insurance1.getId()).isEqualTo(saved.getId());
    }

    @Test
    public void shouldUpdateAndFetchInsurance() {
        Insurance saved = insuranceService.create(insuranceDTO);
        Insurance insurance1 = insuranceService.getById(saved.getId());
        insuranceDTO.setPolicyNumber("updateAndFetchInsurance");
        insuranceService.update(insurance1, insuranceDTO);
        Insurance insurance2 = insuranceService.getById(saved.getId());
        assertThat(insurance2.getPolicyNumber()).isEqualTo("updateAndFetchInsurance");
    }

    @Test
    public void shouldDeleteFetchInsurance() {
        Insurance saved = insuranceService.create(insuranceDTO);
        Insurance insurance1 = insuranceService.getById(saved.getId());
        insuranceRepository.deleteById(insurance1.getId());
        try {
            insuranceService.getById(insurance1.getId());
        } catch (Exception ex) {
            assertThat(ex).hasMessage("Entity is not found");
        }
    }
            @Test
    public void shouldReturnResourceNotFoundException() {
        try {
            insuranceService.getById("shouldReturnResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            assertThat(ex).hasMessage("Entity is not found");
        }
    }


}
