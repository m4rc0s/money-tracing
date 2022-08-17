package me.emkt.cashflowservice

import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Document("accounts")
data class Account(
    @MongoId
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val tags: List<String> = emptyList()
)

data class Money(
    val amount: BigDecimal,
    val code: String = "BRL"
)

@Document("assets")
data class Asset(
    val id: UUID = UUID.randomUUID(),
    val description: String,
    val amount: BigDecimal
)

@Document("entries")
data class Entry(
    val id: UUID = UUID.randomUUID(),
    val amount: BigDecimal,
    val description: String,
    val type: EntryType,
    @DBRef
    val account: Account,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

@Document("balances")
data class Balance(
    val id: UUID = UUID.randomUUID(),
    val accountId: String,
    val amount: BigDecimal,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)


enum class EntryType {
    DEBIT,
    CREDIT
}
