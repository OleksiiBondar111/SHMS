package com.shms.api.mapper;

import com.shms.api.dto.insurance.InsuranceDTO;
import com.shms.api.model.insurance.Insurance;
import org.mapstruct.Mapper;

@Mapper
public interface InsuranceMapper extends EntityMapper<Insurance, InsuranceDTO> {
}
