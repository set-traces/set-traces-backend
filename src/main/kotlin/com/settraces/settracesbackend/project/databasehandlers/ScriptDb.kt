package com.settraces.settracesbackend.project.databasehandlers

import com.settraces.settracesbackend.database.ParamHolder
import com.settraces.settracesbackend.project.mappers.RoleMetaMapper
import com.settraces.settracesbackend.project.models.RoleMeta
import com.settraces.settracesbackend.project.models.Script
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Component

@Component
class ScriptDb {
    @Bean
    fun getScripDb(): ScriptDb {
        return ScriptDb()
    }

    @Autowired
    val jdbcTemplate: JdbcTemplate? = null

    @Autowired
    val namedParameterJdbcTemplate: NamedParameterJdbcTemplate? = null

    fun newRole(roleMeta: RoleMeta): RoleMeta {
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        val data: ParamHolder = ParamHolder()

        data.addValue("role", roleMeta.role)
        data.addValue("description", roleMeta.description)
        data.addValue("scriptId", roleMeta.scriptId)
        var sql: String = ""
        if (roleMeta.actorId == null) {
            sql = "insert into playing_roles(role, description, script_id) values (:role, :description, uuid(:scriptId))"
        } else {
            data.addValue("actorId", roleMeta.actorId)
            sql = "insert into playing_roles(role, actor_id, description, script_id) values (:role, uuid(:actorId), :description, uuid(:scriptId))"
        }
        val update = namedParameterJdbcTemplate!!.update(sql, data, keyHolder)
        val roleMetaId: String = keyHolder.keyList.get(0).get("id").toString()
        return getRoleMetaById(roleMetaId)!!
    }

    fun getRoleMetaById(id: String): RoleMeta? {
        return namedParameterJdbcTemplate!!.queryForObject("select * from playing_roles pr left join actors a on a.id = pr.actor_id where pr.id=uuid(:id)", MapSqlParameterSource().addValue("id", id), RoleMetaMapper())
    }
}