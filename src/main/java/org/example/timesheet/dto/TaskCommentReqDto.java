package org.example.timesheet.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskCommentReqDto {
     private UUID weekId;
     private String comment;
     private UUID commented_by;
}
