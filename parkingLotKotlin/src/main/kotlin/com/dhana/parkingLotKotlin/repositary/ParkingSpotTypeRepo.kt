package com.dhana.parkingLotKotlin.repositary

import com.dhana.parkingLotKotlin.entity.ParkingSpotType
import org.springframework.data.jpa.repository.JpaRepository

interface ParkingSpotTypeRepo : JpaRepository<ParkingSpotType,String> {
    fun findAllBy(): Set<ParkingSpotType?>?
}