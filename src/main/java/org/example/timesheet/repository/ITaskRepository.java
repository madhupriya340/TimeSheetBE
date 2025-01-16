package org.example.timesheet.repository;


import org.example.timesheet.dto.AddTaskRequestDto;
import org.example.timesheet.dto.DayWiseTasksDto;
import org.example.timesheet.dto.TaskCommentReqDto;
import org.example.timesheet.dto.UpdateTaskRequestDto;
import org.example.timesheet.model.Tasks;
import org.hibernate.annotations.Comments;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ITaskRepository {
    public void addTask(AddTaskRequestDto task);
    public List<Tasks> getTasksById(UUID consultantId, UUID projectId, Date weekStartDate);
    public void addComments(TaskCommentReqDto comment);
    public void updateTask(UpdateTaskRequestDto task);
    public void deleteTask(UUID taskId);
}
