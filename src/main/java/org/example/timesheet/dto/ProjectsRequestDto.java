package org.example.timesheet.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectsRequestDto {
    private UUID projectId;
    private String projectCode;
    private String projectName;
    private String projectDesc;
    private Date startDate;
    private Date endDate;
    private String projectStatus;
    private String ProjectManagerName;
}
