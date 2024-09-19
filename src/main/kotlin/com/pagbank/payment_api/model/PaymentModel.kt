package com.pagbank.payment_api.model

import com.pagbank.payment_api.enums.PaymentMethodEnum
import com.pagbank.payment_api.enums.PaymentStatusEnum
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Table(name="payments")
@Entity
data class PaymentModel(

    @Id
    val id: String,
    var amount: BigDecimal,
    var currency: String,
    @Enumerated(EnumType.STRING)
    var paymentMethod: PaymentMethodEnum,
    var description: String,
    val customerName: String,
    val customerCpf: String,
    val customerAddress: String,
    @Enumerated(EnumType.STRING)
    val status: PaymentStatusEnum,
    val createdAt: LocalDateTime = LocalDateTime.now()

)
