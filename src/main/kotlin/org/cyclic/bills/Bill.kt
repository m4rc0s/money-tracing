package org.cyclic.bills

import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Document("bills")
data class Bill (
    @MongoId
    val id: UUID = UUID.randomUUID(),
    val dueDate: LocalDate,
    val description: String,
    val amount: BigDecimal,
    val issuer: String,
    val overdue: Boolean = false
)

@Document("payments")
data class Payment (
    @MongoId
    val id: UUID = UUID.randomUUID(),
    val date: LocalDateTime,
    val amount: BigDecimal,
    val description: String,
    @DBRef
    val bill: Bill,
    val paymentMethod: PaymentMethod,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

enum class PaymentMethod {
    BILLET,
    CASH,
    ACCOUNT_DEBT,
    BANK_TRANSFER,
    DEBT_CARD,
    CREDIT_CARD
}
