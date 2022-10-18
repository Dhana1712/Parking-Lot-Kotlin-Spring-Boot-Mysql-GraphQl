package com.dhana.parkingLotKotlin.service

import com.dhana.parkingLotKotlin.entity.Ticket
import java.time.LocalDateTime

interface TicketService {
    fun getbyId(id: Int): Ticket?
    fun getAll(): Set<Ticket?>?
    fun park(parkInput: Map<String?, Any?>): Ticket?

    fun unPark(unParkInput: Map<String?, Int?>?): Ticket?

    fun hours(from: LocalDateTime?, to: LocalDateTime?): Float

    fun fare(totalHours: Float,farePerHour : Int?): Float

    fun update(ticket: Ticket): Ticket?

}