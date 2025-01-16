package org.example.timesheet.service;

import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.dto.UserInfoRequest;
import org.example.timesheet.dto.UserInfoResponseDto;
import org.example.timesheet.model.UserInfo;
import org.example.timesheet.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse createUser(UserInfoRequest user) {
        UserInfo addUser = UserInfo.builder()
                .username(user.getUserName())
                .email(user.getEmail())
                .isActive(user.isActive())
                .password(user.getPassword())
                .roleIds(user.getRoleIds())
                .build();
        userRepository.save(addUser);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(),"Successfully created user",user,null );
        return apiResponse;
    }

    @Override
    public ApiResponse getUsersByType(String type) {
       List<UserInfoResponseDto> usersByType= userRepository.getUsersByType(type);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(),"Successfully Retrieved",usersByType,null );
        return apiResponse;

    }
    public ApiResponse getAllUsers() {
        List<UserInfoResponseDto> users = userRepository.getAllUsers();
        ApiResponse apiResponse=new ApiResponse(HttpStatus.CREATED.value(),"Successfully Retrieved",users,null);
        return apiResponse;
    }

    @Override
    public ApiResponse updateUserInfo(UUID userId,UserInfo userInfo) {
        userRepository.updateUserInfo(userId,userInfo);
        ApiResponse res= new ApiResponse(HttpStatus.OK.value(),"Successfully Updated",userInfo,null);
        return res;
    }
}
