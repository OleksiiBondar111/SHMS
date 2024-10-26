package com.shms.api.dao.users;

import com.shms.api.dao.SoftDeleteJpaRepository;
import com.shms.api.model.auth.user.UserEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends SoftDeleteJpaRepository<UserEntity, String> {

    @Query("select e from #{#entityName} e where e.deletedAt is null order by e.createdAt asc")
    List<UserEntity> findAllByOrderByCreatedAtAsc();

}
