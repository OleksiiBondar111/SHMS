package com.shms.api.model.auth.authorities;

import com.shms.api.dto.auth.authorities.AuthorityDTO;
import com.shms.api.enums.Authority;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
public class AuthorityEntity {

    @Id
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, updatable = false)
    protected String id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Authority name;

    @ManyToMany(mappedBy = "authorities")
    private List<RoleEntity> roles;

    public AuthorityEntity(Authority name) {
        this.name = name;
    }

    public AuthorityEntity(AuthorityDTO authorityDTO) {
        this.name = authorityDTO.getName();
    }
}
