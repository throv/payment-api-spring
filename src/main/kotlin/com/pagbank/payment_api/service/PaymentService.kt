package com.pagbank.payment_api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.pagbank.payment_api.dto.CustomerDto
import com.pagbank.payment_api.dto.PaymentDto
import com.pagbank.payment_api.enums.PaymentMethodEnum
import com.pagbank.payment_api.enums.PaymentStatusEnum
import com.pagbank.payment_api.exceptions.InvalidPaymentMethodException
import com.pagbank.payment_api.exceptions.NoPaymentFoundException
import com.pagbank.payment_api.model.PaymentModel
import com.pagbank.payment_api.repository.PaymentRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.NoSuchElementException

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    private val client = OkHttpClient()

    fun getCustomerInfo(): String {
        val request = Request.Builder()
            .url("http://localhost:8082/api/v2/customer")
            .build()

        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw RuntimeException("Failed to fetch customer info: ${response.code}")
                }
                response.body?.string() ?: throw RuntimeException("Empty response body")
            }
        } catch (e: Exception) {
            throw RuntimeException("Error while fetching customer info: ${e.message}")
        }
    }

    fun createNewPayment(newPayment: PaymentDto): PaymentModel {

        try {

            val customerJson = getCustomerInfo()
            val objectMapper = ObjectMapper()
            val customerDto = objectMapper.readValue(customerJson, CustomerDto::class.java)

            val payment = PaymentModel(
                id = "PAY-" + UUID.randomUUID().toString(),
                amount = newPayment.amount,
                currency = newPayment.currency,
                paymentMethod = newPayment.paymentMethod,
                description = newPayment.description,
                customerName = customerDto.customerName,
                customerCpf = customerDto.customerCpf,
                customerAddress = customerDto.customerAddress,
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