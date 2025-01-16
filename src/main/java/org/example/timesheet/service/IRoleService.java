package org.example.timesheet.service;

import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.model.Role;

public interface IRoleService {
    ApiResponse addRole(Role role);
    ApiResponse getAllRoles();
}
