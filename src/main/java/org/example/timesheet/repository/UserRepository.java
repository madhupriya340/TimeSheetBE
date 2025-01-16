package org.example.timesheet.repository;

import org.example.timesheet.constants.PostgresqlConstants;
import org.example.timesheet.dto.UserInfoRequest;
import org.example.timesheet.dto.UserInfoResponseDto;
import org.example.timesheet.model.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.example.timesheet.constants.PostgresqlConstants.UserConstants;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository implements IUserRepository {
    private final  JdbcTemplate jdbcTemplate;
    public UserRepository( JdbcTemplate jdbcTemplate1) {
        this.jdbcTemplate = jdbcTemplate1;
    }
    @Override
    public void save(UserInfo user) {
        String sql=UserConstants.Add_User;
        jdbcTemplate.update(sql,user.getEmail(),user.getPassword(),user.getUsername());
        UserInfo getUser= getUserByEmail(user.getEmail());
        if(user.getRoleIds()!=null){
            for(UUID roleIds : user.getRoleIds()) {
                jdbcTemplate.update(UserConstants.ADD_ROLES, getUser.getUserId(),roleIds);

            }
        }

    }

    public UserInfo getUserByEmail(String email) {
        String sql= UserConstants.GET_USER_BY_EMAIL;
        return jdbcTemplate.queryForObject(sql,new UserRowMapper(),email);
    }
    @Override
    public List<UserInfoResponseDto> getAllUsers() {
        String sql=UserConstants.GET_ALL_Users;
        return jdbcTemplate.query(sql,(rs, rowNum) ->
                UserInfoResponseDto.builder()
                        .user_id(rs.getObject("user_id", UUID.class))
                        .username(rs.getString("username"))
                        .role_name(rs.getString("role_name"))
                        .build());
    }

    @Override
    public List<UserInfoResponseDto> getUsersByType(String type) {
        System.out.println("get users method enteres");
        String sql=UserConstants.GET_USERS_BY_TYPE;
        System.out.println("query"+sql);
        return jdbcTemplate.query(sql,(rs, rowNum) ->
                UserInfoResponseDto.builder()
                        .user_id(rs.getObject("user_id", UUID.class))
                        .username(rs.getString("username"))
                        .role_name(rs.getString("role_name"))
                        .build(),type);
    }

    @Override
    public void updateUserInfo(UUID userId,UserInfo user) {
        String sql= UserConstants.UPDATE_USER;
        jdbcTemplate.update(sql,user.getUsername(),userId);
    }
}
