package com.settraces.settracesbackend

import com.settraces.settracesbackend.database.Migrate
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SetTracesBackendApplication

fun main(args: Array<String>) {
	runApplication<SetTracesBackendApplication>(*args)
	val shouldMigrate: Boolean = false // set true to make migrations NB! Never change in production
	if (shouldMigrate) {
		val migration_manager: Migrate = Migrate()
		migration_manager.migrate()
	}
}
