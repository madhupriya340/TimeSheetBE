package org.example.timesheet.service;

import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.model.Projects;

import java.util.UUID;

public interface IProjectService {
    ApiResponse addProject(Projects project);
    ApiResponse getProjects(UUID projectId);
    ApiResponse getAllProjects();
    ApiResponse updateProject(Projects project);
    ApiResponse deleteProject(String projectCode);
}
