package com.shms.api.service.impl;

import com.shms.api.dao.auth.UsersRepository;
import com.shms.api.dto.auth.users.UserDTO;
import com.shms.api.exception.ResourceNotFoundException;
import com.shms.api.model.auth.user.UserEntity;
import com.shms.api.service.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UsersServiceImpl implements EntityService<UserEntity, UserDTO> {

    private final UsersRepository usersRepository;
    private final RoleServiceImpl rolesService;

    @Override
    public UserEntity create(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity(userDTO);
        if (!CollectionUtils.isEmpty(userEntity.getRoles())) {
            userEntity.setRoles(userDTO.getRoles().stream()
                    .map(roleDTO -> {
                        var id = rolesService.getByName(roleDTO.getName());
                        if (id != null) {
                            return id;
                        } else {
                            return rolesService.create(roleDTO);
                        }
                    }).collect(Collectors.toList()));
        }
        return usersRepository.save(userEntity);
    }

    @Override
    public void update(UserEntity entity, UserDTO userDTO) {

    }

    @Override
    public UserEntity getById(String id) {
        return usersRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
