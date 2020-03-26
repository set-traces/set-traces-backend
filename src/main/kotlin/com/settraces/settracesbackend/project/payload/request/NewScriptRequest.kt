package com.settraces.settracesbackend.project.payload.request

import javax.validation.constraints.NotBlank

class NewScriptRequest(var name: @NotBlank String, var projectId: @NotBlank String, var typeId: @NotBlank String, var description: String) {

}