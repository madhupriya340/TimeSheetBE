package org.example.timesheet.service;

import org.apache.catalina.User;
import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.dto.UserInfoRequest;
import org.example.timesheet.model.UserInfo;

import java.util.UUID;

public interface IUserService {
    ApiResponse createUser(UserInfoRequest user);
    ApiResponse getUsersByType(String type);
    ApiResponse getAllUsers();
    ApiResponse updateUserInfo(UUID userId, UserInfo userInfo);
}
