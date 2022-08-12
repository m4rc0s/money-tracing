package me.emkt.cashflowservice

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.math.BigDecimal
import java.util.UUID

@Document("bank_accounts")
data class BankAccount(
    @MongoId
    val id: UUID = UUID.randomUUID(),
    val balance: Money = Money(
        amount = BigDecimal.ZERO
    ),
    val bankName: String,
    val tags: List<String> = emptyList()
)

data class Money(
    val amount: BigDecimal,
    val code: String = "BRL"
)
