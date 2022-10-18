package com.dhana.parkingLotKotlin.service

import com.dhana.parkingLotKotlin.entity.Payment

interface PaymentService {
    fun cardPayment(ticketId: Int): Payment?
    fun cashPayment(ticketId: Int): Payment?
}