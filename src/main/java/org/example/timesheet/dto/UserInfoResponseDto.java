package org.example.timesheet.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponseDto {
    private UUID user_id;
    private String username;
    private String role_name;


}
