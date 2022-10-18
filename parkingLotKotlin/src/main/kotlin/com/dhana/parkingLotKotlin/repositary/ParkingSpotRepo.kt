package com.dhana.parkingLotKotlin.repositary

import com.dhana.parkingLotKotlin.Enum.ParkingSpotQuota
import com.dhana.parkingLotKotlin.Enum.ParkingSpotStatus
import com.dhana.parkingLotKotlin.entity.ParkingFloor
import com.dhana.parkingLotKotlin.entity.ParkingSpot
import com.dhana.parkingLotKotlin.entity.ParkingSpotType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface ParkingSpotRepo : JpaRepository<ParkingSpot,Int> {
    fun findAllBy(): Set<ParkingSpot?>?

    @Modifying
    @Transactional
    @Query("delete from ParkingSpot where id=:id")
    override fun deleteById(id: Int)

    fun findByParkingSpotNoAndParkingSpotTypeAndParkingFloorAndParkingSpotQuota(
        parkingSpotNo: Int,
        parkingSpotType: ParkingSpotType?,
        parkingFloor: ParkingFloor?,
        parkingSpotQuota: ParkingSpotQuota?
    ): ParkingSpot?

    fun findByParkingSpotTypeAndParkingSpotQuotaAndStatusOrderByParkingSpotNoAsc(
        parkingSpotType: ParkingSpotType?,
        parkingSpotQuota: ParkingSpotQuota?,
        available: ParkingSpotStatus?
    ): List<ParkingSpot?>?
}