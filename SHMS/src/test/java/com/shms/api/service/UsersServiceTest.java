package com.shms.api.service;


import com.shms.api.testBase.UsersTestBase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UsersServiceTest extends UsersTestBase {

//    @Autowired
//    private UsersService usersService;
//
//    @Test
//    public void shouldCreteAndFetchUser() {
//        UserEntity saved = usersService.create(userDTO);
//        UserEntity user = usersService.getById(saved.getId());
//        assertThat(user.getId()).isEqualTo(saved.getId());
//        assertThat(user.getRoles().get(0)).isNotNull();
//        assertThat(user.getRoles().get(0).getAuthorities()).isNotEmpty();
//    }



}
