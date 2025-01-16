package org.example.timesheet.controller;

import jakarta.websocket.server.PathParam;
import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.model.Projects;
import org.example.timesheet.model.Role;
import org.example.timesheet.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<?> addProject(@RequestBody Projects project) {
        ApiResponse res=projectService.addProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/{project_id}")
    public ResponseEntity<?> getProjectById(@PathVariable UUID project_id) {
        ApiResponse res=projectService.getProjects(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        ApiResponse res=projectService.getAllProjects();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PatchMapping
    public ResponseEntity<?> updateProject(@RequestBody Projects project) {
        ApiResponse res=projectService.updateProject(project);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{projectCode}")
    public ResponseEntity<?> deleteProject(@PathVariable("projectCode") String projectCode) {
        ApiResponse res= projectService.deleteProject(projectCode);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
