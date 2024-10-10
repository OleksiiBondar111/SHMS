package com.shms.api.dao.medicalRecord;

import com.shms.api.dao.SoftDeleteJpaRepository;
import com.shms.api.model.medicalRecord.MedicalRecord;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicalRecordRepository extends SoftDeleteJpaRepository<MedicalRecord, String> {

    @Query("select e from #{#entityName} e where e.deletedAt is null order by e.createdAt asc")
    List<MedicalRecord> findAllByOrderByCreatedAtAsc();

}
