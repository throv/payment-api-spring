package com.pagbank.payment_api.controller

import com.pagbank.payment_api.dto.PaymentDto
import com.pagbank.payment_api.model.PaymentModel
import com.pagbank.payment_api.service.PaymentService
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v2/payments")
class PaymentController(
    private val paymentService: PaymentService
    ) {

    private val logger: Logger = LoggerFactory.getLogger(PaymentController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPayment(@Valid @RequestBody paymentBody: PaymentDto): PaymentModel {

        logger.info("Received request to create new payment")

        val newPayment = paymentService.createNewPayment(paymentBody)

        logger.info("Successfully processed create payment request")
        return newPayment
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPaymentById(@PathVariable id: String) : PaymentModel {

        logger.info("Received request to get payment with ID: $id")

        val payment = paymentService.getPaymentById(id)
        return payment
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updatePayment(@PathVariable id: String,
                      @Valid @RequestBody updatePayment: PaymentDto
    ): PaymentModel? {
        return paymentService.updatePayment(id, updatePayment)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePayment(@PathVariable id: String) {
        paymentService.deletePayment(id)
    }
}