package com.settraces.settracesbackend.project.mappers

import com.settraces.settracesbackend.project.models.RoleMeta
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class RoleMetaMapper : RowMapper<RoleMeta> {
    override fun mapRow(rs: ResultSet, rowNum: Int): RoleMeta? {
        return RoleMeta(rs.getString("role"), rs.getString("description"), rs.getString("actor"))
    }
}