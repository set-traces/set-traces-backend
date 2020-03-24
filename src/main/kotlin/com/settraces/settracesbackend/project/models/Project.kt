package com.settraces.settracesbackend.project.models

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import kotlin.collections.HashSet


@Entity
@Table(name = "projects", uniqueConstraints = [UniqueConstraint(columnNames = ["id"])])
class Project {
    @Id val id: UUID = UUID.randomUUID()
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    var name: @NotBlank @Size(max=50) String? = null
    @Type(type="text")
    var description: @NotBlank String? = null

    constructor() {}
    constructor(name: String, description: String):this() {
        this.name = name;
        this.description = description;
    }
}