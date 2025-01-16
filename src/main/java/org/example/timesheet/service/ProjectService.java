package org.example.timesheet.service;

import org.example.timesheet.dto.ApiResponse;
import org.example.timesheet.dto.ProjectsRequestDto;
import org.example.timesheet.model.Projects;
import org.example.timesheet.repository.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService implements IProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ApiResponse addProject(Projects project) {
        projectRepository.addProject(project);
        ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(),"Successfully Created",project,null);

        return apiResponse;
    }

    @Override
    public ApiResponse getProjects(UUID projectId) {
        Projects projects=projectRepository.getProject(projectId);
        ApiResponse apiResponse= new ApiResponse(HttpStatus.OK.value(),"Successfully Retrieved",projects,null);
        return apiResponse;
    }

    @Override
    public ApiResponse getAllProjects() {
        List<ProjectsRequestDto> projectsRequestDto=projectRepository.getAllProjects();
        ApiResponse apiResponse= new ApiResponse(HttpStatus.OK.value(),"Successfully Retrieved",projectsRequestDto,null);
        return apiResponse;
    }

    @Override
    public ApiResponse updateProject(Projects project) {
        projectRepository.updateProject(project);
        ApiResponse apiResponse= new ApiResponse(HttpStatus.OK.value(),"Successfully Updated",project,null);
        return apiResponse;
    }

    @Override
    public ApiResponse deleteProject(String projectCode) {
        projectRepository.deleteProject(projectCode);
        ApiResponse apiResponse= new ApiResponse(HttpStatus.OK.value(),"Successfully Deleted",true,null);
        return apiResponse;
    }

}
