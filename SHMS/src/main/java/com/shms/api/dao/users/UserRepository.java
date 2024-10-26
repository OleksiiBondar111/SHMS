package com.shms.api.dao.doctor;

import com.shms.api.dao.SoftDeleteJpaRepository;
import com.shms.api.model.doctor.Doctor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends SoftDeleteJpaRepository<Doctor, String> {

    @Query("select e from #{#entityName} e where e.deletedAt is null order by e.createdAt asc")
    List<Doctor> findAllByOrderByCreatedAtAsc();

}
