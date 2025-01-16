package org.example.timesheet.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddTaskRequestDto {
     String taskTitle;
     String taskDesc;
     Date taskDate;
     Float workedHrs;
     Date weekStartDt;
     UUID project_id;
     UUID consultant_id;
}
