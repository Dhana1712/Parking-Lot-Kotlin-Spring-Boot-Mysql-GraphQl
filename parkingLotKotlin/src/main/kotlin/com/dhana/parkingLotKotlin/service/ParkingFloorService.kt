package com.dhana.parkingLotKotlin.service

import com.dhana.parkingLotKotlin.entity.ParkingFloor

interface ParkingFloorService {
    fun getAll(): Set<ParkingFloor?>?

    fun getById(id: Int?): ParkingFloor?

    fun create(parkingFloorInput: Map<String?, String?>): ParkingFloor?

    fun delete(parkingFloorInput: Map<String?, String?>)

    fun getByFloorNoAndParkingLotId(floorNo: Int?, parkingLotId: String?): ParkingFloor?
}