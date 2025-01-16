package org.example.timesheet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    public Role(String roleName){
        this.roleName = roleName;
    }
    public Role(UUID roleId){
        this.roleId = roleId;
    }

    private UUID roleId;
    private String roleName;

}
