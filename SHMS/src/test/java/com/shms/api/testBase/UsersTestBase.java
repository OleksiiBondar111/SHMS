package com.shms.api.testBase;

import com.shms.api.model.auth.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class UsersTestBase {

    protected UserEntity userEntity;


    @BeforeEach
    public void setUp() {
        // Initialize mock objects and data here
        MockitoAnnotations.openMocks(this);
//        authorityDTO = new AuthorityDTO();
//        authorityDTO.setName(Authority.READ);
//        roleDTO = new RoleDTO();
//        roleDTO.setName(Role.ROLE_USER);
//        roleDTO.setAuthoritiesDTO(Arrays.asList(authorityDTO));
//        userDTO = new UserDTORequest();
//        userDTO.setFirstName("John");
//        userDTO.setLastName("Doe");
//        userDTO.setEmail("john@doe.com");
//        userDTO.setPassword("password");
//        userDTO.setRoles(Arrays.asList(roleDTO));
        
    }

}
