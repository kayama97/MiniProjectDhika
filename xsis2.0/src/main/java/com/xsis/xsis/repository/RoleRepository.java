package com.xsis.xsis.repository;

import java.util.List;

import com.xsis.xsis.model.Role;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * RoleRepository
 */
@RepositoryRestResource
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    
    @Query("SELECT r FROM Role r WHERE r.isDelete = FALSE AND (r.code LIKE %?1% OR r.name LIKE %?2%)")
    Iterable<Role> findByCodeOrName(String code, String name);

    @Override
    @Query("SELECT r FROM Role r WHERE r.isDelete=false")
    public List<Role> findAll();

}