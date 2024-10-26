package com.shms.api.model.auth.authorities;

import com.shms.api.dto.auth.authorities.RoleDTO;
import com.shms.api.enums.Role;
import com.shms.api.model.auth.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, updatable = false)
    protected String id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Role name;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "roles_authorities",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
    )
    private List<AuthorityEntity> authorities;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;

    public RoleEntity(Role name) {
        this.name = name;
    }

    public RoleEntity(RoleDTO roleDTO) {
        this.name = roleDTO.getName();
        if (!CollectionUtils.isEmpty(roleDTO.getAuthorities())) {
            this.authorities = roleDTO.getAuthorities().stream()
                    .map(AuthorityEntity::new).collect(Collectors.toList());
            ;
        }
    }
}
