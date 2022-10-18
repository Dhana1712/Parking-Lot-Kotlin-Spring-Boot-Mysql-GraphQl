package com.dhana.parkingLotKotlin.entity

import com.dhana.parkingLotKotlin.Enum.PaymentMethod
import com.dhana.parkingLotKotlin.Enum.PaymentStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? =null

    var amount : Float? =null

    @Enumerated(EnumType.STRING)
    var method : PaymentMethod? =null
    var paidAt : LocalDateTime? =null

    @Enumerated(EnumType.STRING)
    var status : PaymentStatus? =null

    @OneToOne(mappedBy = "payment", cascade = [CascadeType.ALL])
    var ticket : Ticket? =null

}