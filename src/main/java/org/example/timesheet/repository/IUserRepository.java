package org.example.timesheet.repository;

import org.example.timesheet.dto.UserInfoResponseDto;
import org.example.timesheet.model.UserInfo;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {
    public void save(UserInfo user);
    public List<UserInfoResponseDto> getAllUsers();
    public List<UserInfoResponseDto> getUsersByType(String type);
    public void updateUserInfo(UUID userId,UserInfo user);
}
