package com.settraces.settracesbackend.database

import org.springframework.jdbc.core.namedparam.SqlParameterSource

class ParamHolder : SqlParameterSource {
    var data:HashMap<String, Any?>? = null

    constructor() {
        data = HashMap<String, Any?>()
    }

    fun addValue(param: String, d: Any?): ParamHolder {
        data!!.put(param, d)
        return this
    }

    override fun getValue(paramName: String): Any? {
        return data!!.get(paramName)
    }

    override fun hasValue(paramName: String): Boolean {
        return data!!.contains(paramName.toString())
    }


}