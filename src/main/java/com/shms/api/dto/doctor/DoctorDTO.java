package com.shms.api.dto.doctor;

import com.shms.api.dto.EntityDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class DoctorDTO extends EntityDTO {

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotNull(message = "Specialty cannot be null")
    private String specialty;

    private String phone;

    @Email
    private String email;

    @NotNull(message = "Address cannot be null")
    private String officeAddress;

    private String status;

    private String imageId;

    private String availability;

}
