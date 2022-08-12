package me.emkt.cashflowservice

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BankAccountRepository : MongoRepository<BankAccount, UUID> {}

@Repository
interface BucketRepository: MongoRepository<Bucket, UUID> {}