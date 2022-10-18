package com.dhana.parkingLotKotlin.repositary

import com.dhana.parkingLotKotlin.Enum.PanelType
import com.dhana.parkingLotKotlin.entity.EntryExitPanel
import com.dhana.parkingLotKotlin.entity.ParkingLot
import org.springframework.data.jpa.repository.JpaRepository

interface EntryExitPanelRepo : JpaRepository<EntryExitPanel,Int> {
    fun findAllBy(): Set<EntryExitPanel?>?
    fun findByPanelNameAndPanelTypeAndParkingLot(
        panelName: String?,
        panelType: PanelType?,
        parkingLot: ParkingLot?
    ): EntryExitPanel?

}