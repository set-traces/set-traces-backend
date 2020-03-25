package com.settraces.settracesbackend.profile.controllers

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class TestMapper : RowMapper<Test> {
    @Override
    override fun mapRow(rs: ResultSet, rowNum: Int): Test? {
        return Test(rs.getString("name"))
    }
}