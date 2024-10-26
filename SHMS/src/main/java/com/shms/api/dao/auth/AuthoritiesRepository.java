package com.shms.api.dao.auth;

import com.shms.api.enums.Authority;
import com.shms.api.model.auth.authorities.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<AuthorityEntity, String> {
    AuthorityEntity findByName(Authority authority);
}
