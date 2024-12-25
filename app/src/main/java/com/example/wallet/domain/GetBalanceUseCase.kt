package com.example.wallet.domain

import java.math.BigDecimal
import javax.inject.Inject

class GetBalanceUseCase @Inject constructor() {
    suspend operator fun invoke(): BigDecimal {
        return BigDecimal.ONE
    }
}