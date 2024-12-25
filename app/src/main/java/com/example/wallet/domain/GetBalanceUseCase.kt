package com.example.wallet.domain

import java.math.BigDecimal

class GetBalanceUseCase {
    suspend operator fun invoke(): BigDecimal {
        return BigDecimal.ONE
    }
}