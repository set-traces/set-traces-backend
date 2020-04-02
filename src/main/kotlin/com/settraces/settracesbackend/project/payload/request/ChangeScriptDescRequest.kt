package com.settraces.settracesbackend.project.payload.request

import javax.validation.constraints.NotEmpty

class ChangeScriptDescRequest(var description: @NotEmpty String) {
}