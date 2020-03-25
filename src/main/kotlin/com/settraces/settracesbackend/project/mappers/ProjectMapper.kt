package com.settraces.settracesbackend.project.mappers

import com.settraces.settracesbackend.project.models.Project
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class ProjectMapper : RowMapper<Project> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Project? {
        return Project(rs.getString("id"), rs.getString("name"), rs.getString("description"))
    }
}