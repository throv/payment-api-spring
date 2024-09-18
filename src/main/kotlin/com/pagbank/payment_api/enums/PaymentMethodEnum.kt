package com.pagbank.payment_api.enums

enum class PaymentMethodEnum(val value: String) {
    CREDIT_CARD("CREDIT_CARD"),
    DEBIT_CARD("DEBIT_CARD"),
    PIX("PIX")
}