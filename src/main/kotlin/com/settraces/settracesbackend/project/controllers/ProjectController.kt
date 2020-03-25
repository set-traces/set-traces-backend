package com.settraces.settracesbackend.project.controllers

import com.settraces.settracesbackend.project.databasehandlers.ProjectDb
import com.settraces.settracesbackend.project.models.Project
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/project")
class ProjectController {

    @Autowired
    val projectDb: ProjectDb? = null

    @GetMapping("/")
    fun getAll(): List<Project> {
        return projectDb!!.getAll()
    }

}