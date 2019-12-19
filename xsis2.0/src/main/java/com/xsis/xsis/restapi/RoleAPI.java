package com.xsis.xsis.restapi;

import java.util.List;
import java.util.Optional;

import com.xsis.xsis.model.Role;
import com.xsis.xsis.services.RoleServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * RoleAPI
 */
@RestController
@RequestMapping(path = "/api/role", produces = "application/json")
@CrossOrigin(origins = "*")
public class RoleAPI {

    @Autowired
    private RoleServices roleService;

    @GetMapping
    public List<Role> findAll(){
        return roleService.findAll();
    }

    @GetMapping("getAllRole")
    public ResponseEntity<Iterable<Role>> getAllRole(@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10")  Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy){
                Iterable<Role> list = roleService.getAllRole(pageNo, pageSize, sortBy);
                return new ResponseEntity<Iterable<Role>>(list, HttpStatus.OK);
            }
    
    @GetMapping("searchLikeCodeOrName")
    public ResponseEntity<Iterable<Role>> getAllRole(@RequestParam String code, @RequestParam String name){
        Iterable<Role> list = roleService.searchRoleByCodeOrName(code, name);

        return new ResponseEntity<Iterable<Role>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Role> getId(@PathVariable(value="id") Long id){
        return roleService.getById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Role addRole(@RequestBody Role role){
        return roleService.save(role);
    }
    
    @PutMapping(path = "/{id}")
    public Role editRole(@RequestBody Role role){
        return roleService.update(role);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Role deleteRole(@PathVariable("id") Long id){
        return roleService.delete(id);
    }
}