package org.example.timesheet.model;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Projects {
    private UUID projectId;
    private String projectCode;
    private String projectName;
    private String projectDesc;
    private String businessImpact;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
    private String projectStatus;
    private List<UUID> consultantids;
    private List<UUID> managerIds;
}
