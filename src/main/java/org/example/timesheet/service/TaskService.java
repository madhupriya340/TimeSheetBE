package org.example.timesheet.service;

import org.example.timesheet.dto.AddTaskRequestDto;
import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.dto.TaskCommentReqDto;
import org.example.timesheet.dto.UpdateTaskRequestDto;
import org.example.timesheet.model.Tasks;
import org.example.timesheet.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public ApiResponse getTasksByWeekDtAndConsultantId(UUID consultantId, UUID projectId, Date weekStartDt) {
        List<Tasks> tasks=taskRepository.getTasksById(consultantId, projectId,  weekStartDt);
        ApiResponse res= new ApiResponse(HttpStatus.OK.value(),"Successfully fetched",tasks,null );
        return res;
    }

    @Override
    public ApiResponse CreateTasks( AddTaskRequestDto tasks) {
        taskRepository.addTask(tasks);
        ApiResponse res = new ApiResponse(HttpStatus.CREATED.value(),"Successfully created",tasks,null);
        return res;
    }

    @Override
    public ApiResponse AddComment(TaskCommentReqDto taskCommentReqDto) {
        taskRepository.addComments(taskCommentReqDto);
        ApiResponse res= new ApiResponse(HttpStatus.OK.value(),"Successfully added",taskCommentReqDto,null);
        return res;
    }

    @Override
    public ApiResponse updateTask(UpdateTaskRequestDto updateTaskRequestDto) {
        taskRepository.updateTask(updateTaskRequestDto);
        ApiResponse res= new ApiResponse(HttpStatus.OK.value(),"Successfully updated",updateTaskRequestDto,null);
        return res;
    }

    @Override
    public ApiResponse deleteTask(UUID taskId) {
        taskRepository.deleteTask(taskId);
        ApiResponse res= new ApiResponse(HttpStatus.OK.value(),"Successfully deleted",true,null);
        return res;
    }
}
