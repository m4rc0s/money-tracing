package org.cyclic.account

import org.cyclic.cashflowservice.Balance
import org.cyclic.cashflowservice.BalanceRepository
import java.math.BigDecimal
import java.time.temporal.TemporalAmount
import java.util.*

class BalanceService(
    private val balanceRepository: BalanceRepository
) {

    fun checkBalance(accountId: UUID, desiredDebtAmount: BigDecimal) {
        val accountBalance = balanceRepository.findByAccountId(accountId)
        require(accountBalance?.amount!! > desiredDebtAmount) {
            throw Exception("Insufficient founds")
        }
    }

    fun debt(balance: Balance, amount: BigDecimal): Balance =
        balanceRepository.save(balance.also { amount.apply { balance.amount.minus(amount) } })
}