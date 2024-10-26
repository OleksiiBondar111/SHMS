package com.shms.api.service;


import com.shms.api.model.auth.user.UserEntity;
import com.shms.api.service.impl.UsersServiceImpl;
import com.shms.api.testBase.UsersTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class UsersServiceTest extends UsersTestBase {

    @Autowired
    private UsersServiceImpl usersService;

    @Test
    public void shouldCreteAndFetchUser() {
        UserEntity saved = usersService.create(userDTO);
        UserEntity user = usersService.getById(saved.getId());
        assertThat(user.getId()).isEqualTo(saved.getId());
        assertThat(user.getRoles().get(0)).isNotNull();
        assertThat(user.getRoles().get(0).getAuthorities()).isNotEmpty();
    }



}
