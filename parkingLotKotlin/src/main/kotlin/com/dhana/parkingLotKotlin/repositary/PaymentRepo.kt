package com.dhana.parkingLotKotlin.repositary

import com.dhana.parkingLotKotlin.entity.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepo : JpaRepository<Payment,Int> {
}