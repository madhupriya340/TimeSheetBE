package org.example.timesheet.repository;

import org.example.timesheet.dto.ProjectsRequestDto;
import org.example.timesheet.model.Projects;

import java.util.List;
import java.util.UUID;

public interface IProjectRepository {
    public void addProject(Projects project);
    public Projects getProject(UUID project_id);
    public List<ProjectsRequestDto> getAllProjects();
    public Projects getProjectByCode(String project_code);
    public void updateProject(Projects project);
    public void deleteProject(String projectCode);
}
