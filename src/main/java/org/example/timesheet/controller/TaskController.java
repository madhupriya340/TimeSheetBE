package org.example.timesheet.controller;

import org.example.timesheet.dto.AddTaskRequestDto;
import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.dto.TaskCommentReqDto;
import org.example.timesheet.dto.UpdateTaskRequestDto;
import org.example.timesheet.model.Tasks;
import org.example.timesheet.service.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<?> getTasksByWeekDtAndConsultantId(@RequestParam UUID consultantId,@RequestParam UUID projectId,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date weekStartDt){
        ApiResponse res=taskService.getTasksByWeekDtAndConsultantId(consultantId,projectId,weekStartDt);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping
    public ResponseEntity<?> CreateTasks(@RequestBody AddTaskRequestDto tasks){
        ApiResponse res=taskService.CreateTasks(tasks);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("/comments")
    public ResponseEntity<?> CreateTasksComment(@RequestBody TaskCommentReqDto commentReqDto){
        taskService.AddComment(commentReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentReqDto);
    }

    @PatchMapping("/updateTask")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequestDto task){
        taskService.updateTask(task);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTask(@RequestParam UUID taskId){
        ApiResponse res=taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
