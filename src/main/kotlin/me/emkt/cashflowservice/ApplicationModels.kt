package me.emkt.cashflowservice

import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.UUID

@Document("wallets")
data class Wallet(
    val id: UUID = UUID.randomUUID(),
    val buckets: List<Asset> = emptyList()
)

@Document("assets")
data class Asset(
    val id: UUID = UUID.randomUUID(),
    val code: String,
    val description: String,
    val balance: BigDecimal
)
