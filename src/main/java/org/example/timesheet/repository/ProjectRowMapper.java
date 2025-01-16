package org.example.timesheet.repository;

import org.example.timesheet.model.Projects;
import org.example.timesheet.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProjectRowMapper implements RowMapper<Projects> {
    @Override
    public Projects mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Projects.builder()
                .projectId(UUID.fromString(rs.getString("project_id")))
                .projectName(rs.getString("project_name"))
                .projectCode(rs.getString("project_code"))
                .projectDesc(rs.getString("project_desc"))
                .projectStatus(rs.getString("project_status"))
                .businessImpact(rs.getString("business_impact"))
                .startDate(rs.getDate("start_date"))
                .endDate(rs.getDate("end_date"))
                .createdAt(rs.getTimestamp("created_at"))
                .build();
    }
}
