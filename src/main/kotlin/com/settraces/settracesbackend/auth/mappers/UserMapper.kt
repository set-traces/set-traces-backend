package com.settraces.settracesbackend.auth.mappers

import com.settraces.settracesbackend.auth.models.User
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class UserMapper : RowMapper<User> {
    override fun mapRow(rs: ResultSet, rowNum: Int): User? {
        return User(rs.getString("id"),  rs.getString("username"), rs.getString("email"))
    }
}