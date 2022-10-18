package com.dhana.parkingLotKotlin.entity

import com.dhana.parkingLotKotlin.Enum.ElectricStatus
import javax.persistence.*

@Entity
class ElectricBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? =null
    var energyConsumptionCost : Float? =null

    @Enumerated(EnumType.STRING)
    var status : ElectricStatus? =null
}