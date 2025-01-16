package org.example.timesheet.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTaskRequestDto {
    UUID taskId;
    String taskTitle;
    String taskDesc;
    Float workedHrs;

}
