package com.shms.api.dao.payment;

import com.shms.api.dao.SoftDeleteJpaRepository;
import com.shms.api.model.payment.Payment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends SoftDeleteJpaRepository<Payment, String> {

    @Query("select e from #{#entityName} e where e.deletedAt is null order by e.createdAt asc")
    List<Payment> findAllByOrderByCreatedAtAsc();

}
