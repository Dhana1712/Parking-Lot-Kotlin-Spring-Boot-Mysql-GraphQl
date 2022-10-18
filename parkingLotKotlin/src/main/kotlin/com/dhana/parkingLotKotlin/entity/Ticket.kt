package com.dhana.parkingLotKotlin.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? =null
    var vehicleNumber : String? =null
    var vehicleType : String? =null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_panel")
    var exitPanel : EntryExitPanel? =null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exit_panel")
    var entryPanel : EntryExitPanel? = null
    var entryTime : LocalDateTime? =null
    var exitTime : LocalDateTime? =null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_lot_id")
    var parkingLot : ParkingLot? =null

    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH]
    )
    @JoinColumn(name = "parking_spot_id")
    var parkingSpot : ParkingSpot? =null
    var parkingHours : Float? =null
    var parkingFare : Float? =null

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "electric_bill_id")
    var electricBill : ElectricBill? =null

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "payment_id")
    var payment : Payment? = null


}