package org.example.timesheet.repository;

import org.example.timesheet.constants.PostgresqlConstants;
import org.example.timesheet.constants.PostgresqlConstants.TaskConstants;
import org.example.timesheet.dto.AddTaskRequestDto;
import org.example.timesheet.dto.TaskCommentReqDto;
import org.example.timesheet.dto.UpdateTaskRequestDto;
import org.example.timesheet.model.Projects;
import org.example.timesheet.model.Tasks;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class TaskRepository implements ITaskRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProjectRepository projectRepository;
    public TaskRepository(JdbcTemplate jdbcTemplate, ProjectRepository projectRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.projectRepository = projectRepository;
    }

    @Override
    public void addTask(AddTaskRequestDto task) {
        int val=jdbcTemplate.queryForObject(TaskConstants.FIND_WEEKLY_TASK_BY_ID,(rs, rowNum) -> rs.getInt("cnt"),task.getWeekStartDt(),task.getProject_id(),task.getConsultant_id());
        if(val !=1){
            jdbcTemplate.update(TaskConstants.ADD_WEEKLY_TASK,task.getWeekStartDt(),task.getProject_id(),task.getConsultant_id());
        }
        UUID weekId=jdbcTemplate.queryForObject(TaskConstants.GET_WEEKLY_TASK_BY_ID,(rs, rowNum) -> rs.getObject("week_id",UUID.class),task.getWeekStartDt(),task.getProject_id(),task.getConsultant_id());
        String sql= TaskConstants.ADD_Task;
        Projects project= jdbcTemplate.queryForObject(PostgresqlConstants.ProjectConstants.GET_Project_By_ID,new ProjectRowMapper(),task.getProject_id());
        jdbcTemplate.update(sql,weekId, project.getProjectCode(),task.getTaskTitle(),task.getTaskDesc(),task.getTaskDate(),task.getWorkedHrs());
    }

    @Override
    public List<Tasks> getTasksById(UUID consultantId, UUID projectId, Date weekStartDate) {
        String sql=TaskConstants.GET_Tasks_BY_ID;
        List<Tasks> tasks=jdbcTemplate.query(sql,new TaskRowMapper(),consultantId,projectId,weekStartDate,weekStartDate,weekStartDate);
        return tasks;
    }

    @Override
    public void addComments(TaskCommentReqDto comment) {
        String sql=TaskConstants.ADD_COMMENTS;
        jdbcTemplate.update(sql,comment.getWeekId(),comment.getComment(),comment.getCommented_by());
    }

    @Override
    public void updateTask(UpdateTaskRequestDto task) {
        String sql=TaskConstants.UPDATE_TASK_BY_ID;
        jdbcTemplate.update(sql,task.getTaskTitle(),task.getTaskDesc(),task.getWorkedHrs(),task.getTaskId());
    }

    @Override
    public void deleteTask(UUID taskId) {
        String sql=TaskConstants.DELETE_TASK;
        jdbcTemplate.update(sql,taskId);
    }

}
