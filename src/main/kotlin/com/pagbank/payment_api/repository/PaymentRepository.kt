package com.pagbank.payment_api.repository

import com.pagbank.payment_api.model.PaymentModel
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository: JpaRepository<PaymentModel, String> {
}