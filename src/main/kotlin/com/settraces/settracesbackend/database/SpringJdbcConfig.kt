package com.settraces.settracesbackend.database

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource


@Configuration
@ComponentScan("com.baeldung.jdbc")
class SpringJdbcConfig {
    @Bean
    fun mysqlDataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        //dataSource.setDriverClassName("com.mysql.jdbc.Driver") // should change to postgres driver
        dataSource.url = "jdbc:postgresql://localhost:5432/settraces"
        dataSource.username = "settracesdev"
        dataSource.password = "devpass"
        return dataSource
    }
}