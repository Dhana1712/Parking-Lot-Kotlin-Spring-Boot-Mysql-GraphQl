package com.dhana.parkingLotKotlin.resolver

import com.dhana.GraphqlException
import com.dhana.parkingLotKotlin.Enum.ElectricStatus
import com.dhana.parkingLotKotlin.Enum.PanelType
import com.dhana.parkingLotKotlin.Enum.ParkingSpotQuota
import com.dhana.parkingLotKotlin.Enum.ParkingSpotStatus
import com.dhana.parkingLotKotlin.entity.*
import com.dhana.parkingLotKotlin.service.*
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MutationResolver : GraphQLMutationResolver{
    @Autowired
    private lateinit var parkingLotService: ParkingLotService

    @Autowired
    private lateinit var parkingFloorService: ParkingFloorService

    @Autowired
    private lateinit var parkingSpotTypeService: ParkingSpotTypeService

    @Autowired
    private lateinit var ticketService: TicketService

    @Autowired
    private lateinit var parkingSpotService: ParkingSpotService

    @Autowired
    private lateinit var entryExitPanelService: EntryExitPanelService

    @Autowired
    private lateinit var paymentService: PaymentService

    @Autowired
    private lateinit var electricBillService: ElectricBillService



    fun createParkingLot(parkingLot: ParkingLot?): ParkingLot? {
        try {
           return parkingLot?.let { parkingLotService.create(it) }
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }

    fun deleteParkingLot(id: String?): Boolean {
        try {
            parkingLotService.delete(id)
            return true
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }

    fun addParkingFloor(parkingFloorInput: Map<String?, String?>?): ParkingFloor? {
        try {
            return parkingFloorInput?.let { parkingFloorService.create(it) }
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }

    fun removeParkingFloor(parkingFloorInput: Map<String?, String?>?): Boolean? {
        try {
            parkingFloorService.delete(parkingFloorInput!!)
            return true
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun addParkingSpotType(parkingSpotType: ParkingSpotType?): ParkingSpotType? {
        try {
            return parkingSpotType?.let { parkingSpotTypeService.create(it) }
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun removeParkingSpotType(parkingSpotType: String?): Boolean? {
        try {
            parkingSpotTypeService.delete(parkingSpotType)
            return true
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun addParkingSpot(parkingSpotInput: Map<String?, Any?>?): ParkingSpot? {
        try {
            return parkingSpotService.create(parkingSpotInput)
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun removeParkingSpot(parkingSpotDelete: Map<String?, Any?>?): Boolean {
        try {
            parkingSpotService.delete(parkingSpotDelete)
            return true
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun addEntryExitPanel(panelInput: Map<String?, Any?>?): EntryExitPanel? {
        try {
            return entryExitPanelService.create(panelInput)
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun removeEntryExitPanel(panelInput: Map<String?, Any?>?): Boolean? {
        try {
            entryExitPanelService.delete(panelInput)
            return true
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun parkVehicle(entryInput: Map<String?, Any?>): Ticket? {
        try {
            return ticketService.park(entryInput)
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun unParkVehicle(exitInput: Map<String?, Int?>?): Ticket? {
        try {
            return ticketService.unPark(exitInput)
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun cardPayment(id: Int): Payment? {
        try {
            return paymentService.cardPayment(id)
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun cashPayment(id: Int): Payment? {
        try {
            return paymentService.cashPayment(id)
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun chargeVehicle(ticketId: Int): ElectricBill? {
        try {
            return electricBillService.powerOn(ticketId)
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }


    fun unPlugVehicle(unPlugInput: Map<String?, String?>?): ElectricBill? {
        try {
            return electricBillService.powerOff(unPlugInput)
        } catch (ex: RuntimeException) {
            throw GraphqlException(ex)
        }
    }

    fun nestedInputTypes(parkingSpotStatus: ParkingSpotStatus?, panelType: PanelType?, parkingSpotQuota: ParkingSpotQuota?, electricStatus: ElectricStatus?): Boolean? {
        return true
    }

}