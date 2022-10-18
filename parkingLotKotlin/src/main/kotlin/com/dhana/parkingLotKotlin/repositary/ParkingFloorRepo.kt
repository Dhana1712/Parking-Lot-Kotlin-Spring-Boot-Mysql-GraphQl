package com.dhana.parkingLotKotlin.repositary

import com.dhana.parkingLotKotlin.entity.ParkingFloor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface ParkingFloorRepo : JpaRepository<ParkingFloor,Int> {
    fun findAllBy(): Set<ParkingFloor?>?
    fun findByFloorNoAndParkingLotId(floorNo: Int?, id: String?): ParkingFloor?

    @Modifying
    @Transactional
    @Query("delete from ParkingFloor where id=:id")
    override fun deleteById(id: Int)
}