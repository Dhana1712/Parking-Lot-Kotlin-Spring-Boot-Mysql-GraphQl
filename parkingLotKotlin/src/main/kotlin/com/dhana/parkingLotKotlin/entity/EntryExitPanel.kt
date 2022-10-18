package com.dhana.parkingLotKotlin.entity

import com.dhana.parkingLotKotlin.Enum.PanelType
import javax.persistence.*

@Entity
@Table(
    name = "entry_exit_panel",
    uniqueConstraints = [UniqueConstraint(columnNames = ["panel_name", "parking_lot_id", "panel_type"])]
)
class EntryExitPanel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? =null

    @Column(name = "panel_name", nullable = false)
    var panelName : String? = null

    @Column(name = "panel_type", nullable = false)
    @Enumerated(EnumType.STRING)
    var panelType : PanelType? = null

    @ManyToOne
    @JoinColumn(name = "parking_lot_id", nullable = false)
    var parkingLot : ParkingLot? = null


}