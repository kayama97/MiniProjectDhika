package com.xsis.xsis.services;

import java.util.List;
import java.util.Optional;


import com.xsis.xsis.model.Role;


/**
 * RoleServices
 */

public interface RoleServices{


    public List<Role> findAll();
    Iterable<Role> getAllRole(Integer pageNo, Integer pageSize, String sortBy);
    Iterable<Role> searchRoleByCodeOrName(String code, String name);
    Optional<Role> getById(Long id);
    Role save(Role role);
    Role update(Role role);
    Role delete(Long id);
    
}