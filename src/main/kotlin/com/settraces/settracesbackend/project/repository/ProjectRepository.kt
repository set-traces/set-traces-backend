package com.settraces.settracesbackend.project.repository

import com.settraces.settracesbackend.project.models.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProjectRepository : JpaRepository<Project?, Long?> {
    override fun findAll(): MutableList<Project?>
    fun findAllById(id: UUID): Project
}