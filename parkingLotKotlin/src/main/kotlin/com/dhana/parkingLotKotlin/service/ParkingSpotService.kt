package com.dhana.parkingLotKotlin.service

import com.dhana.parkingLotKotlin.Enum.ParkingSpotQuota
import com.dhana.parkingLotKotlin.Enum.ParkingSpotStatus
import com.dhana.parkingLotKotlin.entity.ParkingSpot
import com.dhana.parkingLotKotlin.entity.ParkingSpotType

interface ParkingSpotService {
    fun getAll(): Set<ParkingSpot?>?
    fun getById(id: Int): ParkingSpot?
    fun create(parkingSpotInput: Map<String?, Any?>?): ParkingSpot?

    fun delete(pakingSpotInput: Map<String?, Any?>?)

    fun getAvailableSpots(parkingSpotType: ParkingSpotType?, parkingSpotQuota: ParkingSpotQuota?, available: ParkingSpotStatus?): List<ParkingSpot?>?

    fun getSpot(parkingSpotType: ParkingSpotType?, parkingSpotQuota: ParkingSpotQuota?): ParkingSpot?

    fun update(parkingSpot: ParkingSpot): ParkingSpot?
}