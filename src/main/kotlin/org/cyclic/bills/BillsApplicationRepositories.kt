package org.cyclic.bills

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BillRepository : MongoRepository<Bill, UUID> {}

@Repository
interface PaymentRepository: MongoRepository<Payment, UUID> {}
