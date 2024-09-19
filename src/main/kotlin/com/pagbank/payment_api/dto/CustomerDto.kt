package com.pagbank.payment_api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CustomerDto(
    @JsonProperty("customer_name")
    val customerName: String,
    @JsonProperty("customer_cpf")
    val customerCpf: String,
    @JsonProperty("customer_address")
    val customerAddress: String
)