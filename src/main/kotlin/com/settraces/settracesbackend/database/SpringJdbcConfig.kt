package com.settraces.settracesbackend.database

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource


@Configuration
@ComponentScan("com.baeldung.jdbc")
class SpringJdbcConfig {
    @Bean
    fun mysqlDataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        //dataSource.setDriverClassName("com.mysql.jdbc.Driver") // keep commented when chaging dev env // should change to postgres driver



        // managed
//        dataSource.url = "jdbc:postgresql://settraces-dev-1.czlmbjejpdry.eu-west-1.rds.amazonaws.com:5432/settracesdev"
//        dataSource.password = "QmaOoasl9ZEGJseD9pZ4"
//        dataSource.username = "settraces"

        if (System.getenv("environment") != null && System.getenv("environment").equals("Docker")) { // docker
            println("using docker db")
            dataSource.url = "jdbc:postgresql://database:5432/settraces" // jdbc:postgresql://localhost:5432/settraces
            dataSource.username = "settracesdev"
            dataSource.password = "devpass"
        } else {        // local
            println("using local db")
            dataSource.url = "jdbc:postgresql://localhost:5432/settraces" // jdbc:postgresql://localhost:5432/settraces
            dataSource.username = "settracesdev"
            dataSource.password = "devpass"
        }


        return dataSource
    }

    @Bean
    fun getNJdbcTemplate(): NamedParameterJdbcTemplate {
        val dataSource: DataSource = mysqlDataSource()
        return NamedParameterJdbcTemplate(dataSource!!)
    }

}