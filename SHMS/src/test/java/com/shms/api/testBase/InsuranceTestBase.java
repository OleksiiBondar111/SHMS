package com.shms.api.testBase;

import com.shms.api.dto.insurance.InsuranceDTO;
import com.shms.api.model.insurance.Insurance;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class InsuranceTestBase {

    protected InsuranceDTO insuranceDTO;
    protected Insurance insurance;
    protected List<InsuranceDTO> insuranceDTOList;
    protected List<Insurance> insuranceList;

    @BeforeEach
    public void setUp() {
        // Initialize mock objects and data here
        MockitoAnnotations.openMocks(this);
        insurance = new Insurance();
        insurance.setProviderName("ProviderName");
        insurance.setCoverageDetails("CoverageDetails");
        insurance.setPolicyNumber("PolicyNumber");
        insurance.setId("Id");
        insuranceDTO = new InsuranceDTO(insurance);
        insuranceDTOList = List.of(insuranceDTO);
        insuranceList = List.of(insurance);
        
    }

}
