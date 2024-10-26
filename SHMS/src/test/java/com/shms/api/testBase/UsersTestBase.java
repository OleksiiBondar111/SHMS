package com.shms.api.testBase;

import com.shms.api.dto.auth.authorities.AuthorityDTO;
import com.shms.api.dto.auth.authorities.RoleDTO;
import com.shms.api.dto.auth.users.UserDTO;
import com.shms.api.enums.Authority;
import com.shms.api.enums.Role;
import com.shms.api.model.auth.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

public class UsersTestBase {

    protected UserDTO userDTO;
    protected RoleDTO roleDTO;
    protected AuthorityDTO authorityDTO;
    protected UserEntity userEntity;


    @BeforeEach
    public void setUp() {
        // Initialize mock objects and data here
        MockitoAnnotations.openMocks(this);
        authorityDTO = new AuthorityDTO();
        authorityDTO.setName(Authority.READ);
        roleDTO = new RoleDTO();
        roleDTO.setName(Role.ROLE_USER);
        roleDTO.setAuthoritiesDTO(Arrays.asList(authorityDTO));
        userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("john@doe.com");
        userDTO.setPassword("password");
        userDTO.setRoles(Arrays.asList(roleDTO));
        
    }

}
