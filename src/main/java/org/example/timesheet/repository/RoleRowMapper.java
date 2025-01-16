package org.example.timesheet.repository;

import org.example.timesheet.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Role(
                rs.getObject("role_id", UUID.class),
                rs.getString("role_name")
        );
    }
}
