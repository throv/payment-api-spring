package com.pagbank.payment_api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.pagbank.payment_api.enums.PaymentMethodEnum
import jakarta.validation.constraints.Size
import java.math.BigDecimal


data class PaymentDto(


    //@JsonProperty("customer_id")
    //val customerId: String,
    var amount: BigDecimal,
    var currency: String,
    var paymentMethod: PaymentMethodEnum,
    @Size(min=20, max =50)
    var description: String
)
