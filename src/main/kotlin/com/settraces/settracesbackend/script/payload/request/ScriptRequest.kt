package com.settraces.settracesbackend.script.payload.request

import javax.validation.constraints.NotBlank

class ScriptRequest {
    var name: @NotBlank String? = null
    var description: String? = null
    var type: @NotBlank String? = null
}