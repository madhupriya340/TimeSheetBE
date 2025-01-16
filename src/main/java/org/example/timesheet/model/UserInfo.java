package org.example.timesheet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    private UUID userId;
    private String username;
    private String email;
    private String password;
    private String consultantType;
    private String consultantFrm;
    private String createdAt;
    private String updatedAt;
    private Boolean isActive;
    private List<UUID> roleIds;

    public UserInfo(UUID userId,String username, String email, boolean isActive, String password, String consultantType, String createdAt, String updatedAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.isActive = isActive;
        this.password = password;
        this.consultantType = consultantType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }
}
