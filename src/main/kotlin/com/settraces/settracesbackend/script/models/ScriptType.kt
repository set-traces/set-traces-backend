package com.settraces.settracesbackend.script.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.settraces.settracesbackend.project.models.Project
import com.settraces.settracesbackend.project.models.Script
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(name = "script_types", uniqueConstraints = [UniqueConstraint(columnNames = ["id"])])
class ScriptType {
    @Id
    val id: UUID = UUID.randomUUID()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var name: @NotBlank @Size(max=50) String? = null

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    var project: Project? = null

    @JsonBackReference
    @OneToMany(mappedBy = "script_type", fetch = FetchType.LAZY,
            cascade = [CascadeType.ALL])
    var scripts: Set<Script>? = null
}