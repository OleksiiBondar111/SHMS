package com.shms.api.service.impl;

import com.shms.api.dao.auth.RolesRepository;
import com.shms.api.dto.auth.authorities.RoleDTO;
import com.shms.api.enums.Role;
import com.shms.api.model.auth.authorities.RoleEntity;
import com.shms.api.service.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements EntityService<RoleEntity, RoleDTO> {

    private final RolesRepository rolesRepository;
    private final AuthorityServiceImpl authorityService;


    @Override
    public RoleEntity create(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity(roleDTO);
        if (!CollectionUtils.isEmpty(roleEntity.getAuthorities())) {
            roleEntity.setAuthorities(
                    roleDTO.getAuthorities().stream()
                            .map(authorityEntity -> {
                                var id = authorityService.getByAuthority(authorityEntity.getName());
                                if (id != null) {
                                    return id;
                                } else {
                                    return authorityService.create(authorityEntity);
                                }
                            }).collect(Collectors.toList())
            );
        }
        return rolesRepository.save(roleEntity);
    }

    @Override
    public void update(RoleEntity entity, RoleDTO roleDTO) {

    }

    @Override
    public RoleEntity getById(String id) {
        return null;
    }

    public RoleEntity getByName(Role name) {
        return rolesRepository.findByName(name);
    }
}
