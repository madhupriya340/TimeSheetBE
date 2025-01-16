package org.example.timesheet.service;

import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.model.Role;
import org.example.timesheet.repository.IRolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    private final IRolesRepository rolesRepository;
    public RoleService(IRolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public ApiResponse addRole(Role role) {
         rolesRepository.save(role);
         ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(),"Successfully Role Created",role,null);
         return apiResponse;
    }

    @Override
    public ApiResponse getAllRoles() {
        List<Role> roles=rolesRepository.getAllROles();
        if(roles.size()==0){
            return  new ApiResponse(HttpStatus.NOT_FOUND.value(),"Role Not Found",null,null);
        }
        return new ApiResponse(HttpStatus.OK.value(),"Successfully fecthed Roles",roles,null);
    }
}
