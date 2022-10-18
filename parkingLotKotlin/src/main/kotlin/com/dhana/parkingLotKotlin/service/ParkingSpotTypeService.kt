package com.dhana.parkingLotKotlin.service

import com.dhana.parkingLotKotlin.entity.ParkingSpotType

interface ParkingSpotTypeService {
    fun getAll(): Set<ParkingSpotType?>?
    fun getById(id: String?): ParkingSpotType?
    fun create(parkingSpotType: ParkingSpotType): ParkingSpotType?
    fun delete(id: String?)
}