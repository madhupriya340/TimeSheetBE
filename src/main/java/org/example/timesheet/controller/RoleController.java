package org.example.timesheet.controller;

import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.model.Role;
import org.example.timesheet.service.IRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final IRoleService roleService;

    public RoleController(IRoleService roleService){

        this.roleService = roleService;
    }
    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody Role role){
        ApiResponse res= roleService.addRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    @GetMapping
    public ResponseEntity<?> getRoles(){
        ApiResponse res= roleService.getAllRoles();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
