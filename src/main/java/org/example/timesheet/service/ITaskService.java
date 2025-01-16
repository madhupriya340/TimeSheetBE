package org.example.timesheet.service;

import org.example.timesheet.dto.AddTaskRequestDto;
import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.dto.TaskCommentReqDto;
import org.example.timesheet.dto.UpdateTaskRequestDto;
import org.example.timesheet.model.Tasks;

import java.util.Date;
import java.util.UUID;

public interface ITaskService {
    public ApiResponse getTasksByWeekDtAndConsultantId(UUID consultantId, UUID projectId, Date weekStartDt);
    public ApiResponse CreateTasks(AddTaskRequestDto tasks);
    public ApiResponse AddComment(TaskCommentReqDto taskCommentReqDto);
    public ApiResponse updateTask(UpdateTaskRequestDto updateTaskRequestDto);
    public ApiResponse deleteTask(UUID taskId);
}
