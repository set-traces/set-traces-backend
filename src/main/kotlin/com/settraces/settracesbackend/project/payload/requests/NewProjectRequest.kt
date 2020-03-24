package com.settraces.settracesbackend.project.payload.requests

import javax.validation.constraints.NotBlank

class NewProjectRequest {
    var name: @NotBlank String? = null
    var description: @NotBlank String? = null
}