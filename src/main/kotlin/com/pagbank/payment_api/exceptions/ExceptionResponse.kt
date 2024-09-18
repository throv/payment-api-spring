package com.pagbank.payment_api.exceptions

import java.util.Date

data class ExceptionResponse(
    val message: String?,
    val details: String,
    val timeStamp: Date,

)
