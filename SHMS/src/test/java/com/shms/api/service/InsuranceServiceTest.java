package com.shms.api.service;


import com.shms.api.dao.insurance.InsuranceRepository;
import com.shms.api.dto.InsuranceDTO;
import com.shms.api.model.Insurance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class InsuranceServiceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private InsuranceRepository insuranceRepository;

    static InsuranceDTO insurance = new InsuranceDTO();

    @BeforeAll
    public static void initInsurance() {
        insurance.setProviderName("Insurance Provider");
        insurance.setCoverageDetails("Coverage Details");
        insurance.setPolicyNumber("Policy Number");
    }

    @Test
    public void creteAndFetchInsurance() {
        Insurance saved = insuranceService.create(insurance);
        Insurance insurance1 = insuranceService.getById(saved.getId());
        assertThat(insurance1.getId()).isEqualTo(saved.getId());
    }

    @Test
    public void updateAndFetchInsurance() {
        Insurance saved = insuranceService.create(insurance);
        Insurance insurance1 = insuranceService.getById(saved.getId());
        insurance.setPolicyNumber("updateAndFetchInsurance");
        insuranceService.update(insurance1, insurance);
        Insurance insurance2 = insuranceService.getById(saved.getId());
        assertThat(insurance2.getPolicyNumber()).isEqualTo("updateAndFetchInsurance");
    }

    @Test
    public void deleteFetchInsurance() {
        Insurance saved = insuranceService.create(insurance);
        Insurance insurance1 = insuranceService.getById(saved.getId());
        insuranceRepository.deleteById(insurance1.getId());
        try {
            insuranceService.getById(insurance1.getId());
        } catch (Exception ex) {
            assertThat(ex).hasMessage("Entity is not found");
        }
    }


}
