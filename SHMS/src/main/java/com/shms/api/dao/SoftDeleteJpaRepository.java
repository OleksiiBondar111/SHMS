package com.shms.api.dao;

import com.shms.api.model.IdEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface SoftDeleteJpaRepository<T extends IdEntity, ID extends Serializable>
  extends JpaRepository<T, ID> {

  @Override
  @Query("select e from #{#entityName} e where e.deletedAt is null")
  List<T> findAll();

  @Override
  @Query("select e from #{#entityName} e where e.deletedAt is null and e.id in ?1")
  List<T> findAllById(Iterable<ID> ids);

  @Override
  @Transactional
  @Modifying
  @Query("update #{#entityName} e set e.deletedAt = CURRENT_TIMESTAMP  where e.id=?1")
  void deleteById(ID id);

  @Override
  @Transactional
  @Modifying
  @Query("update #{#entityName} e set e.deletedAt = CURRENT_TIMESTAMP ")
  void deleteAll();

  @Override
  @Query("select count(e) from #{#entityName} e where e.deletedAt is null")
  long count();

  @Query("select e from #{#entityName} e where e.deletedAt is null and e.id = ?1")
  Optional<T> findOneById(ID id);

  @Query("select e from #{#entityName} e")
  List<T> findAllWithDeleted();

  @Query("select e from #{#entityName} e where e.id in ?1")
  List<T> findAllWithDeletedById(Iterable<ID> ids);
}
