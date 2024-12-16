package com.shms.api.dto.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shms.api.dto.EntityDTO;
import com.shms.api.dto.insurance.InsuranceDTO;
import com.shms.api.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class PatientDTO extends EntityDTO {

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotNull(message = "Date of birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    private String phone;

    @Email
    private String email;

    @NotNull(message = "Address cannot be null")
    private String address;

    private String status;

    private String imageId;

    private InsuranceDTO insurance;

}
