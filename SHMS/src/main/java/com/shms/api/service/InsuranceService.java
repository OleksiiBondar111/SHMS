package com.shms.api.service;

import com.shms.api.dto.insurance.InsuranceDTO;
import com.shms.api.model.insurance.Insurance;

public interface InsuranceService {
    Insurance create(InsuranceDTO insuranceDTO);

    void update(Insurance insurance, InsuranceDTO insuranceDTO);

    Insurance getById(String id);

}
