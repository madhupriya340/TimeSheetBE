package org.example.timesheet.model;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeeklyTasks {
    private UUID weekId;
    private Date weekStartDt;
    private String status;
    private UUID approvedBy;
    private Date approvedDt;
    private Date submittedDt;
    private UUID projectID;
    private UUID consultantId;
}
