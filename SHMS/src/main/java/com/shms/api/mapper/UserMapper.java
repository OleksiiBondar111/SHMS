package com.shms.api.mapper;

import com.shms.api.dto.auth.users.UserDTO;
import com.shms.api.model.auth.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends EntityMapper<UserEntity, UserDTO> {

}
