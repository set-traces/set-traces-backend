package com.settraces.settracesbackend.project.payload.request
import javax.validation.constraints.NotBlank

class NewProjectRequest(var name: @NotBlank String, var description: String) {

}