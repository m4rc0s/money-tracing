package org.cyclic.bills

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*


@RestController
@RequestMapping("/bills")
class BillsController{

    @Autowired
    private lateinit var billPaymentUseCase: BillPaymentUseCase

    @Autowired
    private lateinit var billRepository: BillRepository

    @PostMapping
    fun create(@RequestBody createBillDTO: CreateBillDTO): Bill {
        val bill = Bill(
            dueDate = createBillDTO.dueDate,
            amount = createBillDTO.amount,
            issuer = createBillDTO.issuer,
            description = createBillDTO.description,
            overdue = createBillDTO.overdue
        )
        return billRepository.save(bill)
    }

    @GetMapping
    fun loadBills(): List<Bill> {
        return billRepository.findAll()
    }

    @PostMapping("/{billId}/payment")
    fun pay(@PathVariable billId: UUID, @RequestBody billPaymentDTO: BillPaymentDTO) {
        billPaymentUseCase.execute(
            billId = billId,
            debtAccountId = billPaymentDTO.accountId,
            method = billPaymentDTO.method,
            description = billPaymentDTO.description
        )
    }
}

data class CreateBillDTO(
    val dueDate: LocalDate,
    val amount: BigDecimal,
    val description: String,
    val issuer: String,
    val overdue: Boolean = false
)

data class BillPaymentDTO(
    val accountId: UUID,
    val method: PaymentMethod,
    val description: String?
)