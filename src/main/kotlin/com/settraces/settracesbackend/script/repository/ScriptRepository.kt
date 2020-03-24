package com.settraces.settracesbackend.script.repository

import com.settraces.settracesbackend.project.models.Script
import org.springframework.data.jpa.repository.JpaRepository

interface ScriptRepository : JpaRepository<Script?, Long?> {
    override fun findAll(): MutableList<Script?>
}