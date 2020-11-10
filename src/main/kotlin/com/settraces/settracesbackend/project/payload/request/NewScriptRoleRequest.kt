package com.settraces.settracesbackend.project.payload.request

import javax.validation.constraints.NotBlank

class NewScriptRoleRequest(var role: @NotBlank String, var actorId: String?, var description: String?) {
}