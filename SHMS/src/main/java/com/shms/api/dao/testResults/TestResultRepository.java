package com.shms.api.dao.testResults;

import com.shms.api.dao.SoftDeleteJpaRepository;
import com.shms.api.model.testResult.TestResult;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestResultRepository extends SoftDeleteJpaRepository<TestResult, String> {

    @Query("select e from #{#entityName} e where e.deletedAt is null order by e.createdAt asc")
    List<TestResult> findAllByOrderByCreatedAtAsc();

}
