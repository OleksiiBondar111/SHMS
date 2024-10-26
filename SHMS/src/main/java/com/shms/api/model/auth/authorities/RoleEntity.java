package com.shms.api.model.auth.authorities;

import com.shms.api.enums.Roles;
import com.shms.api.model.auth.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, updatable = false)
    protected String id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Roles name;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "role_authorities",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
    )
    private List<Authority> authorities;
    
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
