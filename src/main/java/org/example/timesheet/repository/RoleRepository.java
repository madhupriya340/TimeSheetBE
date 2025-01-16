package org.example.timesheet.repository;

import org.example.timesheet.constants.PostgresqlConstants.RoleConstants;
import org.example.timesheet.model.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository implements IRolesRepository{
    private final JdbcTemplate jdbcTemplate;
    public RoleRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void save(Role role) {
        String sql=RoleConstants.Add_Role;
        int addRole=jdbcTemplate.update(sql,role.getRoleName());
        System.out.println(addRole);
    }

    @Override
    public List<Role> getAllROles() {
        String sql=RoleConstants.GET_ALL_Roles;
        return jdbcTemplate.query(sql,new RoleRowMapper());
//        return role;
    }
}
