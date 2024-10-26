package com.shms.api.service;

import com.shms.api.dto.auth.users.UserDTO;

public interface UsersService {
    UserDTO createUser(UserDTO user);
}
