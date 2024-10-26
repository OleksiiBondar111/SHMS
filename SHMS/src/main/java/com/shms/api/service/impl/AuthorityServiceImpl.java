package com.shms.api.service.impl;


import com.shms.api.dao.auth.AuthoritiesRepository;
import com.shms.api.dto.auth.authorities.AuthorityDTO;
import com.shms.api.enums.Authority;
import com.shms.api.exception.ResourceNotFoundException;
import com.shms.api.model.auth.authorities.AuthorityEntity;
import com.shms.api.service.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorityServiceImpl implements EntityService<AuthorityEntity, AuthorityDTO> {

    private final AuthoritiesRepository authoritiesRepository;

    @Override
    public AuthorityEntity create(AuthorityDTO authorityDTO) {
        AuthorityEntity authorityEntity = new AuthorityEntity(authorityDTO);
        return authoritiesRepository.save(authorityEntity);
    }

    @Override
    public void update(AuthorityEntity authorityEntity, AuthorityDTO authorityDTO) {
        authorityEntity.setName(authorityDTO.getName());
        authoritiesRepository.saveAndFlush(authorityEntity);
    }

    @Override
    public AuthorityEntity getById(String id) {
        return authoritiesRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public AuthorityEntity getByAuthority(Authority authority) {
        return authoritiesRepository.findByName(authority);
    }

}
