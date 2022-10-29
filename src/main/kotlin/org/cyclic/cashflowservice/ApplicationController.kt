package org.cyclic.cashflowservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.net.URI
import java.util.*

@RestController
@RequestMapping(value = ["/"])
class ApplicationController {

    @Autowired
    private lateinit var bankAccountRepository: AccountRepository

    @Autowired
    private lateinit var assetRepository: AssetRepository

    @Autowired
    private lateinit var entryRepository: EntryRepository

    @PostMapping("accounts")
    fun create(@RequestBody createAccountRequest: CreateAccountDTO): ResponseEntity<Account> {
        val account = Account(
            name = createAccountRequest.name,
            description = createAccountRequest.description,
            tags = createAccountRequest.tags,
        ).also {
            bankAccountRepository.save(it);
        }

        return ResponseEntity
            .created(URI("/accounts/${account.id}"))
            .body(account)
    }

    @PostMapping("assets")
    fun create(@RequestBody createAssetRequest: CreateAssetDTO): ResponseEntity<Asset> {
        val asset = Asset(
            description = createAssetRequest.description,
            amount = createAssetRequest.amount
        ).also {
            assetRepository.save(it)
        }

        return ResponseEntity.created(URI("/assets/${asset.id}"))
            .body(asset)
    }

    @PostMapping("cash-flow/entries")
    fun create(@RequestBody createEntryRequest: CreateEntryDTO): ResponseEntity<Entry> {
        val entry = Entry(
            amount = createEntryRequest.amount,
            description = createEntryRequest.description,
            type = createEntryRequest.type,
            account = bankAccountRepository.findById(UUID.fromString(createEntryRequest.destination.accountId)).get()
        ).also {
            entryRepository.save(it)
        }

        return ResponseEntity.ok().body(entry);
    }

    @GetMapping("cash-flow/entries", params = ["accountId"])
    fun entries(): ResponseEntity<AccountEntriesDTO> {
        return ResponseEntity.ok().body(AccountEntriesDTO(
            entries = entryRepository.findAll().map {
                EntryDTO(
                    id = it.id.toString(),
                    amount = it.amount.toString(),
                    type = it.type.name,
                    account = EntryDTO.AccountDTO(it.account.id.toString()),
                    createdAt = it.createdAt.toString(),
                    updatedAt = it.updatedAt.toString()
                )
            }
        ))
    }

}

data class CreateAssetDTO(
    val amount: BigDecimal,
    val description: String
)

data class CreateAccountDTO(
    val name: String,
    val description: String,
    val tags: List<String>
)

data class CreateEntryDTO(
    val amount: BigDecimal,
    val description: String,
    val type: EntryType,
    val destination: Destination
) {
    data class Destination(
        val accountId: String
    )
}

data class EntryDTO(
    val id: String,
    val amount: String,
    val type: String,
    val account: AccountDTO,
    val createdAt: String,
    val updatedAt: String
) {
    data class AccountDTO(
        val id: String
    )
}
data class AccountEntriesDTO(
    val entries: List<EntryDTO>
)
