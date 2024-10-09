package com.shms.api.dao.insurance;

import com.shms.api.dao.SoftDeleteJpaRepository;
import com.shms.api.model.Insurance;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceRepository extends SoftDeleteJpaRepository<Insurance, String> {
    
    @Query("select e from #{#entityName} e where e.deletedAt is null order by e.createdAt asc")
    List<Insurance> findAllByOrderByCreatedAtAsc();

}
