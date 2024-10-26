package com.shms.api.dao.auth;

import com.shms.api.enums.Role;
import com.shms.api.model.auth.authorities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RoleEntity, String> {
    RoleEntity findByName(Role name);
}
