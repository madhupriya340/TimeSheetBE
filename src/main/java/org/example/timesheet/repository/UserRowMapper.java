package org.example.timesheet.repository;

import org.example.timesheet.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<UserInfo> {
    @Override
    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserInfo(
                rs.getObject("user_id",UUID.class),
                rs.getString("username"),
                rs.getString("email"),
                rs.getBoolean("is_active"),
                rs.getString("password"),
                rs.getString("consultant_type"),
                rs.getString("created_at"),
                rs.getString("updated_at")

        );
    }
}
//{
//        "userName":"Madhu Priya",
//        "email":"madhupriya.rayi@gmail.com",
//        "password":"madhu@340",
//        "isActive":true,
//        "role":{
//        "roleId":"f946b3b6-38e8-43f5-844a-68f181887a83",
//        "roleName":"Admin"
//        }
//        }
