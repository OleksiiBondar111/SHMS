package com.shms.api.dao.invoice;

import com.shms.api.dao.SoftDeleteJpaRepository;
import com.shms.api.model.invoice.Invoice;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends SoftDeleteJpaRepository<Invoice, String> {

    @Query("select e from #{#entityName} e where e.deletedAt is null order by e.createdAt asc")
    List<Invoice> findAllByOrderByCreatedAtAsc();

}
