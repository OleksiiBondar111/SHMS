package com.shms.api.model.auth.user;


import com.shms.api.dto.auth.users.UserDTO;
import com.shms.api.model.TrackedEntity;
import com.shms.api.model.auth.authorities.RoleEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity extends TrackedEntity {

    @Id
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, updatable = false)
    protected String id;

    @Column(nullable = false, name = "user_id")
    private String userId;

    @Column(nullable = false, name = "firstName")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "encrypted_password")
    private String encryptedPassword;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<RoleEntity> roles;

    public UserEntity(UserDTO userDTO) {
        this.userId = UUID.randomUUID().toString();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.encryptedPassword = userDTO.getEncryptedPassword();
        if (!CollectionUtils.isEmpty(userDTO.getRoles())) {
            this.roles = userDTO.getRoles().stream().map(RoleEntity::new).collect(Collectors.toList());
        }
    }

}
