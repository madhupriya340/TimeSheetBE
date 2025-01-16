package org.example.timesheet.model;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tasks {
    private UUID taskId;
    private UUID week_id;
    private String taskCode;
    private String taskTitle;
    private String taskDesc;
    private Date taskDate;
    private Float workedHrs;
    private Date createdDt;
    private Date updatedDt;

}
