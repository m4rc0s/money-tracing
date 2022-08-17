package me.emkt.cashflowservice

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountRepository : MongoRepository<Account, UUID> {}

@Repository
interface AssetRepository: MongoRepository<Asset, UUID> {}

@Repository
interface EntryRepository: MongoRepository<Entry, UUID> {}