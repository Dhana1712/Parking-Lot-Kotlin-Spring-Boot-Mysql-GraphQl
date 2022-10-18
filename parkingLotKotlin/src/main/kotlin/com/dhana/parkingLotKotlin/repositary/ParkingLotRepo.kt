package com.dhana.parkingLotKotlin.repositary

import com.dhana.parkingLotKotlin.entity.ParkingLot
import org.springframework.data.jpa.repository.JpaRepository

interface ParkingLotRepo : JpaRepository<ParkingLot,String>{
    fun findAllBy(): Set<ParkingLot?>?
}