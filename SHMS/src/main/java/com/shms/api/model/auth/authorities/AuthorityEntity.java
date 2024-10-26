package com.shms.api.model.auth.authorities;

import com.shms.api.enums.Authorities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, updatable = false)
    protected String id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Authorities name;

    @ManyToMany(mappedBy = "authorities")
    private List<Role> roles;

    public Authority(Authorities name) {
        this.name = name;
    }
}
