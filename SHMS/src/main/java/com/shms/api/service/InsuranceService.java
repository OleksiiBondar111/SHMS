package com.shms.api.service;

import com.shms.api.dto.InsuranceDTO;
import com.shms.api.model.Insurance;

public interface InsuranceService {
    Insurance create(InsuranceDTO insuranceDTO);

    void update(Insurance insurance, InsuranceDTO insuranceDTO);

    Insurance getById(String id);

}
