package com.dhana.parkingLotKotlin.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ParkingSpotType {

    @Id
    @Column(name = "parking_spot_type")
    var parkingSpotType: String? = null

    @Column(nullable = false)
    var farePerHour : Int? = null
}