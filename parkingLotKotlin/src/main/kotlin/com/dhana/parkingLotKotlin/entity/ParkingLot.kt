package com.dhana.parkingLotKotlin.entity

import javax.persistence.*

@Entity
class ParkingLot {
    @Id
    var id : String? = null
    var name : String? =null
    var description : String? =null

    @OneToMany(mappedBy = "parkingLot",cascade = [CascadeType.ALL],fetch = FetchType.EAGER)
    var parkingFloors : Set<ParkingFloor> = HashSet()

    @OneToMany(mappedBy = "parkingLot", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var tickets : Set<Ticket> = HashSet()

    @OneToMany(mappedBy = "parkingLot",cascade = [CascadeType.ALL],fetch = FetchType.EAGER)
    var entryExitPanels : Set<EntryExitPanel> = HashSet()


}