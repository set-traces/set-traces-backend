package com.settraces.settracesbackend.project.databasehandlers

import com.settraces.settracesbackend.database.ParamHolder
import com.settraces.settracesbackend.project.models.ScriptType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Component

@Component
class ScriptTypeDb {
    @Bean
    fun getScriptTypeDb(): ScriptTypeDb {
        return ScriptTypeDb()
    }

    @Autowired
    val jdbcTemplate: JdbcTemplate? = null

    @Autowired
    val namedParameterJdbcTemplate: NamedParameterJdbcTemplate? = null


    fun create(scriptType: ScriptType): ScriptType {
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        val rows: Int = namedParameterJdbcTemplate!!.update("insert into script_types (name, project_id) values (:name, uuid(:projectId))", ParamHolder().addValue("name", scriptType.name).addValue("projectId", scriptType.projectId), keyHolder)
        val id: String = keyHolder.keyList.get(0).get("id").toString()
        scriptType.id = id
        return scriptType
    }
}