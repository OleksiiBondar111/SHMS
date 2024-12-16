package com.shms.api.dto.insurance;

import com.shms.api.dto.EntityDTO;
import com.shms.api.model.insurance.Insurance;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class InsuranceDTO extends EntityDTO {

    @NotNull(message = "Provider name cannot be null")
    private String providerName;

    @NotNull(message = "Policy number cannot be null")
    private String policyNumber;

    private String coverageDetails;

    public InsuranceDTO(Insurance insurance) {
        this.id = insurance.getId();
        this.providerName = insurance.getProviderName();
        this.policyNumber = insurance.getPolicyNumber();
        this.coverageDetails = insurance.getCoverageDetails();
        this.lastModified = insurance.getLastModified();
        this.createdAt = insurance.getCreatedAt();
    }
}
