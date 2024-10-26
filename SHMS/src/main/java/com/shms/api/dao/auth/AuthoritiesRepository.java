package com.shms.api.dao.auth;

import com.shms.api.model.auth.authorities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository  extends JpaRepository<Role, String> {


}
