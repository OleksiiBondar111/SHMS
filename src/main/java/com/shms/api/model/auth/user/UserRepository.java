package com.shms.api.model.auth.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Cacheable("user")
    Optional<UserEntity> findByEmail(String email);

    @CacheEvict({"user", "token"})
    UserEntity save(UserEntity userEntity);

}