package com.settraces.settracesbackend.project.payload.request

import javax.validation.constraints.NotBlank

class NewScriptTypeRequest(var name: @NotBlank String) {
}