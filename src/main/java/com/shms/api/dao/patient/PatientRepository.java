package com.shms.api.dao.patient;

import com.shms.api.dao.SoftDeleteJpaRepository;
import com.shms.api.model.patient.Patient;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends SoftDeleteJpaRepository<Patient, String> {
    
    @Query("select e from #{#entityName} e where e.deletedAt is null order by e.createdAt asc")
    List<Patient> findAllByOrderByCreatedAtAsc();

}
