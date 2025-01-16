package org.example.timesheet.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayWiseTasksDto {
    private String day;
    private String taskTitle;
    private double workedHrs;
}
