package com.pagbank.payment_api.exceptions

data class InvalidPaymentMethodException (
    val exception: String?
) : RuntimeException(exception)