package me.emkt.cashflowservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.net.URI

@RestController
@RequestMapping(value = ["/"])
class BucketsController {

    @Autowired
    private lateinit var repository: BankAccountRepository

    @PostMapping("bank-accounts")
    fun create(@RequestBody createAccountRequest: CreateAccountDTO): ResponseEntity<BankAccount> {
        val account = BankAccount(
            bankName = createAccountRequest.bankName,
            tags = createAccountRequest.tags
        ).also {
            repository.save(it);
        }

        return ResponseEntity
            .created(URI("/bank-accounts/${account.id}"))
            .body(account)
    }

    @PostMapping("assets")
    fun create(@RequestBody createAssetRequest: CreateAssetDTO): ResponseEntity<Asset> {
        val asset = Asset(
            code = createAssetRequest.code,
            description = createAssetRequest.description,
            balance = BigDecimal.ZERO
        )

        return ResponseEntity.created(URI("/assets/${asset.id}"))
            .body(asset)
    }

}

data class CreateAssetDTO(
    val code: String,
    val description: String
)

data class CreateAccountDTO(
    val bankName: String,
    val tags: List<String>
)
