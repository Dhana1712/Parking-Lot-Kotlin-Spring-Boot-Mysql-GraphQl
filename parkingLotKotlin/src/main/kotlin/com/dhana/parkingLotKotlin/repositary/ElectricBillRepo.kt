package com.dhana.parkingLotKotlin.repositary

import com.dhana.parkingLotKotlin.entity.ElectricBill
import org.springframework.data.jpa.repository.JpaRepository

interface ElectricBillRepo : JpaRepository<ElectricBill,Int>{
}