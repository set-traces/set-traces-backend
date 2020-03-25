package com.settraces.settracesbackend.project.databasehandlers

import com.settraces.settracesbackend.project.mappers.ProjectMapper
import com.settraces.settracesbackend.project.models.Project
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.SQLException

@Component
class ProjectDb {

    @Bean
    fun getProjectDb(): ProjectDb {
        return ProjectDb()
    }

    @Autowired
    val jdbcTemplate: JdbcTemplate? = null

    fun getAll(): List<Project> {
        return try {
            jdbcTemplate!!.query("select * from projects", ProjectMapper())
        } catch (e: SQLException) {
            ArrayList()
        }
    }
}