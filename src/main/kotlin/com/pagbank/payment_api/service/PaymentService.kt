package com.pagbank.payment_api.service

import com.pagbank.payment_api.dto.PaymentDto
import com.pagbank.payment_api.enums.PaymentMethodEnum
import com.pagbank.payment_api.enums.PaymentStatusEnum
import com.pagbank.payment_api.exceptions.InvalidPaymentMethodException
import com.pagbank.payment_api.exceptions.NoPaymentFoundException
import com.pagbank.payment_api.model.PaymentModel
import com.pagbank.payment_api.repository.PaymentRepository
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.NoSuchElementException

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    fun createNewPayment(newPayment: PaymentDto): PaymentModel {

        try {
            val payment = PaymentModel(
                id = "PAY-" + UUID.randomUUID().toString(),
                amount = newPayment.amount,
                currency = newPayment.currency,
                paymentMethod = newPayment.paymentMethod,
                description = newPayment.description,
                status = PaymentStatusEnum.PENDING,
                createdAt = LocalDateTime.now()
            )

            return paymentRepository.save(payment)
        } catch(e: HttpMessageNotReadableException) {
            throw InvalidPaymentMethodException("Payment method \"${ newPayment.paymentMethod }\" is not valid")
        }

    }

    fun getPaymentById(id: String): PaymentModel {

        try {
                return paymentRepository
                .findById(id)
                .get()
        } catch (e: NoSuchElementException) {
            throw NoPaymentFoundException("No payments found with id: $id")
        }

    }

    fun updatePayment(
        id: String,
        paymentInfoUpdate: PaymentDto
    ): PaymentModel {

        val paymentToUpdate = paymentRepository.findById(id)

        if(paymentToUpdate.isPresent) {
            val existingPayment = paymentToUpdate.get()

            existingPayment.amount = paymentInfoUpdate.amount
            existingPayment.currency = paymentInfoUpdate.currency
            existingPayment.paymentMethod = paymentInfoUpdate.paymentMethod
            existingPayment.description = paymentInfoUpdate.description

            return paymentRepository.save(existingPayment)
        } else {
            throw Exception("No payments were found with id $id")
        }
    }

    fun deletePayment(id: String) {
        paymentRepository.deleteById(id)
    }


}