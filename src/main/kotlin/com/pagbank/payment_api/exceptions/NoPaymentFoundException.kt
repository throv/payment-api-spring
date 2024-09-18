package com.pagbank.payment_api.exceptions

data class NoPaymentFoundException (
    val exception: String?
) : RuntimeException(exception)
