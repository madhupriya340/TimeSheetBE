package org.example.timesheet.repository;

import org.example.timesheet.model.Tasks;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TaskRowMapper implements RowMapper<Tasks> {
    @Override
    public Tasks mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Tasks.builder()
                .taskId(UUID.fromString(rs.getString("task_id")))
                .taskCode(rs.getString("task_code"))
                .taskTitle(rs.getString("task_title"))
                .taskDesc(rs.getString("task_desc"))
                .taskDate(rs.getTimestamp("task_date"))
                .workedHrs(rs.getFloat("worked_hrs"))
                .createdDt(rs.getDate("created_dt"))
                .updatedDt(rs.getDate("updated_dt"))
                .build();
    }

}
