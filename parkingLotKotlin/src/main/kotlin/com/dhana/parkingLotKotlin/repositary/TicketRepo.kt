package com.dhana.parkingLotKotlin.repositary

import com.dhana.parkingLotKotlin.entity.Ticket
import org.springframework.data.jpa.repository.JpaRepository

interface TicketRepo : JpaRepository<Ticket,Int> {
}