package com.dhana.parkingLotKotlin.service

import com.dhana.parkingLotKotlin.entity.ParkingLot

interface ParkingLotService {

    fun getAll(): Set<ParkingLot?>?
    fun getById(id: String?): ParkingLot?
    fun create(parkingLot: ParkingLot): ParkingLot?
    fun delete(id: String?)
}