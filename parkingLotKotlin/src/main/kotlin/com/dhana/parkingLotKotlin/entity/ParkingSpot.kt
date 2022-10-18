package com.dhana.parkingLotKotlin.entity

import com.dhana.parkingLotKotlin.Enum.ParkingSpotQuota
import com.dhana.parkingLotKotlin.Enum.ParkingSpotStatus
import javax.persistence.*

@Entity
@Table(
    name = "parking_spot",
    uniqueConstraints = [UniqueConstraint(columnNames = ["parking_spot_no", "parking_spot_type", "floor_id", "parking_spot_quota"])]
)
class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null

    @Column(name = "parking_spot_no", nullable = false)
    var parkingSpotNo : Int? =null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_spot_type", nullable = false)
    var parkingSpotType : ParkingSpotType? =null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "floor_id", nullable = false)
    var parkingFloor : ParkingFloor? =null

    @Enumerated(EnumType.STRING)
    @Column(name = "parking_spot_quota", nullable = false)
    var parkingSpotQuota : ParkingSpotQuota? =null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status : ParkingSpotStatus? = null

    @OneToMany(mappedBy = "parkingSpot")
    var tickets : Set<Ticket> = HashSet()

}