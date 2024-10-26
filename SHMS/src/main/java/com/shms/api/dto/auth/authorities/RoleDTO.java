package com.shms.api.dto.auth.authorities;

import com.shms.api.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoleDTO {
    private Role name;
    private List<AuthorityDTO> authorities;
}
