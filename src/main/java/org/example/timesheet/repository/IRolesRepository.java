package org.example.timesheet.repository;

import org.example.timesheet.model.Role;

import java.util.List;

public interface IRolesRepository {
    void save(Role role);
    List<Role> getAllROles();
}
