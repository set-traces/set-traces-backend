package com.settraces.settracesbackend.project.models

import org.springframework.data.annotation.Id

class Project(@Id val id: String?, var name: String?, var description: String?) {
    constructor(name: String?, description: String?) : this(null, name, description)
}