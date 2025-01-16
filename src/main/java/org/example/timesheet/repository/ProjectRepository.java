package org.example.timesheet.repository;

import jakarta.transaction.Transactional;
import org.example.timesheet.dto.ProjectsRequestDto;
import org.example.timesheet.model.Projects;
import org.springframework.jdbc.core.JdbcTemplate;
import org.example.timesheet.constants.PostgresqlConstants.ProjectConstants;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProjectRepository implements IProjectRepository{
    private final JdbcTemplate jdbcTemplate;
    ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    @Transactional
    public void addProject(Projects project) {
        String sql= ProjectConstants.ADD_PROJECT;
         jdbcTemplate.update(sql,project.getProjectCode(),project.getProjectName(),project.getProjectDesc(),project.getBusinessImpact(),project.getStartDate(),project.getEndDate(),project.getProjectStatus());
         Projects addedProject=getProjectByCode(project.getProjectCode());
         if(project.getConsultantids() !=null){
             for(UUID consultantId : project.getConsultantids() ){
                 jdbcTemplate.update(ProjectConstants.ADD_PROJECT_CONSULTANT,addedProject.getProjectId(),consultantId);
             }
         }
        if (project.getManagerIds() != null) {
            for (UUID managerId : project.getManagerIds()) {
                jdbcTemplate.update(ProjectConstants.ADD_PROJECT_MANAGER, addedProject.getProjectId(), managerId);
            }
        }

    }

    @Override
    public Projects getProject(UUID project_id) {
        String sql=ProjectConstants.GET_Project_By_ID;
        Projects project= jdbcTemplate.queryForObject(sql,new ProjectRowMapper(),project_id);
        List<UUID> consultantIds = jdbcTemplate.query(
                ProjectConstants.GET_PROJECT_CONSULTANTS,
                (rs, rowNum) -> UUID.fromString(rs.getString("consultant_id")),
                project_id
        );

        List<UUID> managerIds = jdbcTemplate.query(
                ProjectConstants.GET_PROJECT_MANAGERS,
                (rs, rowNum) -> UUID.fromString(rs.getString("project_manager_id")),
                project_id
        );

        project.setConsultantids(consultantIds);
        project.setManagerIds(managerIds);

        return project;
    }
    public Projects getProjectByCode(String project_code) {
        String sql=ProjectConstants.GET_PROJECT_BY_CODE;
       return jdbcTemplate.queryForObject(sql,new ProjectRowMapper(),project_code);
    }

    @Override
    public void updateProject(Projects project) {
        String sql=ProjectConstants.UPDATE_PROJECT_BY_CODE;
        jdbcTemplate.update(sql,project.getProjectName(),project.getProjectDesc(),project.getBusinessImpact(),project.getProjectStatus(),project.getProjectCode());
        if(project.getConsultantids()!=null){
            for(UUID consultantId : project.getConsultantids() ){

                jdbcTemplate.update(ProjectConstants.UPDATE_PROJECT_CONSULTANTS,consultantId,project.getProjectCode());
            }
        }
        if(project.getManagerIds()!=null){
            for (UUID managerId : project.getManagerIds()) {
                jdbcTemplate.update(ProjectConstants.UPDATE_PROJECT_MANAGERS,managerId,project.getProjectCode());
            }
        }


    }

    @Override
    public void deleteProject(String projectCode) {
        Projects project=getProjectByCode(projectCode);
        if(project.getProjectStatus().toLowerCase().equals("rejected")){
            jdbcTemplate.update(ProjectConstants.DELETE_PROJECT_MANAGERS,project.getProjectId());
            jdbcTemplate.update(ProjectConstants.DELETE_PROJECT_CONSULTANTS,project.getProjectId());
            String sql=ProjectConstants.DELETE_PROJECT;
            jdbcTemplate.update(sql,projectCode);
        }

    }

    public List<ProjectsRequestDto> getAllProjects() {
        String sql=ProjectConstants.GET_ALL_PROJECTS;
        List<ProjectsRequestDto> projects = jdbcTemplate.query(sql, (rs, rowNum) ->
                ProjectsRequestDto.builder()
                        .projectId(rs.getObject("project_id", UUID.class))
                        .projectName(rs.getString("project_name"))
                        .projectDesc(rs.getString("project_desc"))
                        .projectCode(rs.getString("project_code"))
                        .startDate(rs.getDate("start_date"))
                        .endDate(rs.getDate("end_date"))
                        .projectStatus(rs.getString("project_status"))
                        .ProjectManagerName(rs.getString("project_manager_name"))
                        .build()
        );

        return projects;
    }
}
