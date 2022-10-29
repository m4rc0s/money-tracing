package org.cyclic.bills

import jdk.jfr.Description
import org.cyclic.account.BalanceService
import org.cyclic.cashflowservice.AccountRepository
import org.cyclic.cashflowservice.BalanceRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class BillPaymentUseCase(
    private val billRepository: BillRepository,
    private val balanceRepository: BalanceRepository,
    private val paymentRepository: PaymentRepository,
){

    fun execute(billId: UUID, debtAccountId: UUID, method: PaymentMethod, description: String?) {
        val billToPay = billRepository.findById(billId).get();

        val balance = balanceRepository.findByAccountId(debtAccountId)

        val payment = Payment(
            date = LocalDateTime.now(),
            amount = billToPay.amount,
            description = description ?: "Pagamento referente a ${billToPay.description}",
            bill = billToPay,
            paymentMethod = method
        )

        paymentRepository.save(payment).let {
            balance?.debt(billToPay.amount)
            balanceRepository.save(balance!!)
        }
    }
}