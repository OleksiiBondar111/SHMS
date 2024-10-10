package com.shms.api.service.impl;


import com.shms.api.dao.insurance.InsuranceRepository;
import com.shms.api.dto.insurance.InsuranceDTO;
import com.shms.api.exception.ResourceNotFoundException;
import com.shms.api.model.insurance.Insurance;
import com.shms.api.service.InsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;

    @Override
    public Insurance create(InsuranceDTO insuranceDTO) {
        Insurance insurance = new Insurance(insuranceDTO);
        return insuranceRepository.save(insurance);
    }

    @Override
    public void update(Insurance insurance, InsuranceDTO insuranceDTO) {
        insurance.setPolicyNumber(insuranceDTO.getPolicyNumber());
        insurance.setProviderName(insuranceDTO.getProviderName());
        insurance.setCoverageDetails(insuranceDTO.getCoverageDetails());
        insuranceRepository.saveAndFlush(insurance);
    }

    @Override
    public Insurance getById(String id) {
        return insuranceRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

}
