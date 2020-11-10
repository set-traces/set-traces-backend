package com.settraces.settracesbackend.project.payload.request

import javax.validation.constraints.NotEmpty

class ChangeScriptNameRequest(var name: @NotEmpty String) {
}