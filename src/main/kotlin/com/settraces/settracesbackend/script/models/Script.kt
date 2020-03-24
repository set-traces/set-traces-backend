package com.settraces.settracesbackend.project.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.settraces.settracesbackend.script.models.ScriptType
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "scripts", uniqueConstraints = [UniqueConstraint(columnNames = ["id"])])
class Script {
    @Id val id: UUID = UUID.randomUUID()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var name: @NotBlank @Size(max=50) String? = null
    @Type(type="text")
    var description: @NotBlank String? = null

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    var project: Project? = null

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "script_type_id", nullable = false)
    var script_type: ScriptType? = null

    constructor() {}

    constructor(name: String, description: String):this() {
        this.name = name;
        this.description = description;
    }
}