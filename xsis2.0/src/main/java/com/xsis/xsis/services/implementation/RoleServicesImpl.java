package com.xsis.xsis.services.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.xsis.xsis.model.Role;
import com.xsis.xsis.repository.RoleRepository;
import com.xsis.xsis.services.RoleServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * RoleServicesImpl
 */
@Service
@Transactional
public class RoleServicesImpl implements RoleServices {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        for (Role role : roleRepository.findAll()) {
            if (!role.getIsDelete()) {
                roles.add(role);
            }
        }
        return roles;
    }

    @Override
    public Optional<Role> getById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role){
        role.setCreatedBy(2L);
        role.setCreatedOn(new Date());
        role.setIsDelete(false);
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role){
        Role roleDetail = roleRepository.findById(role.getId()).get();
        roleDetail.setCode(role.getCode());
        roleDetail.setName(role.getName());
        roleDetail.setModifiedBy(2L);
        roleDetail.setModifiedOn(new Date());

        return roleRepository.save(roleDetail);
    }

    @Override
    public Role delete(Long id){
        Role roleEntity = roleRepository.findById(id).get();
        roleEntity.setIsDelete(true);
        roleEntity.setDeletedBy(2L);
        roleEntity.setDeleteddOn(new Date());
        return roleRepository.save(roleEntity);
    }

    @Override
    public Iterable<Role> getAllRole(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Role> pagedResult = roleRepository.findAll(paging);
        return pagedResult.getContent();

    }

    @Override
    public Iterable<Role> searchRoleByCodeOrName(String code, String name){
        return roleRepository.findByCodeOrName(code, name);
    }
}