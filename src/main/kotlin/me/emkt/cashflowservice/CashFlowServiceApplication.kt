package me.emkt.cashflowservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class CashFlowServiceApplication

fun main(args: Array<String>) {
	runApplication<CashFlowServiceApplication>(*args)
}
