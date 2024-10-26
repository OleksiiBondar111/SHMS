package com.shms.api.dto.auth.users;

import com.shms.api.dto.auth.authorities.RoleDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    private String lastName;
    @Email
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
    private String userId;
    private String encryptedPassword;
    private List<RoleDTO> roles;
    
}
