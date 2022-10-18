package com.dhana.parkingLotKotlin.service

import com.dhana.parkingLotKotlin.entity.ElectricBill

interface ElectricBillService {
    fun powerOn(ticketId: Int?): ElectricBill?

    fun getById(id: Int?): ElectricBill?

    fun powerOff(powerOffInput: Map<String?, String?>?): ElectricBill?
}