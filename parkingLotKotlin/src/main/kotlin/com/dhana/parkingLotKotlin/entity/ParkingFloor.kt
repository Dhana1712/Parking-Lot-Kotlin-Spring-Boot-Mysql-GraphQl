package com.dhana.parkingLotKotlin.entity

import javax.persistence.*

@Entity
@Table(name = "parking_floor", uniqueConstraints = [UniqueConstraint(columnNames = ["floor_no", "parking_lot_id"])])
class ParkingFloor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null

    @Column(name = "floor_no", nullable = false)
    var floorNo : Int? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_lot_id", nullable = false)
    var parkingLot : ParkingLot? = null

    @OneToMany(mappedBy = "parkingFloor", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var parkingSpots : Set<ParkingSpot>? = null



}