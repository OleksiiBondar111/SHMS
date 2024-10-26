package com.shms.api.dao.auth;

import com.shms.api.model.auth.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmail(String email);
}
