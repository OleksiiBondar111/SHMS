package com.shms.api.dao.appointment;

import com.shms.api.dao.SoftDeleteJpaRepository;
import com.shms.api.model.appointemnt.Appointment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends SoftDeleteJpaRepository<Appointment, String> {
    
    @Query("select e from #{#entityName} e where e.deletedAt is null order by e.createdAt asc")
    List<Appointment> findAllByOrderByCreatedAtAsc();

}
