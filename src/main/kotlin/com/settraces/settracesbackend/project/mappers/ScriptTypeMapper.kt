package com.settraces.settracesbackend.project.mappers

import com.settraces.settracesbackend.project.models.ScriptType
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class ScriptTypeMapper : RowMapper<ScriptType> {
    override fun mapRow(rs: ResultSet, rowNum: Int): ScriptType? {
        return ScriptType(rs.getString("id"), rs.getString("name"), rs.getString("project_id"))
    }
}