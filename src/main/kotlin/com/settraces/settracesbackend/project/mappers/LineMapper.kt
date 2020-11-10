package com.settraces.settracesbackend.project.mappers

import com.settraces.settracesbackend.project.models.Line
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class LineMapper : RowMapper<Line> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Line? {
        return Line(rs.getString("id"), rs.getString("role"), rs.getString("type"), rs.getString("text"), rs.getString("script_id"), rs.getString("prole_id"), rs.getInt("ordering"))
    }
}