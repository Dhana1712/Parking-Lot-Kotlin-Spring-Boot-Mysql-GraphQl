package com.dhana.parkingLotKotlin.resolver

import com.dhana.parkingLotKotlin.entity.ParkingFloor
import com.dhana.parkingLotKotlin.entity.ParkingLot
import com.dhana.parkingLotKotlin.entity.ParkingSpotType
import com.dhana.parkingLotKotlin.entity.Ticket
import com.dhana.parkingLotKotlin.service.ParkingFloorService
import com.dhana.parkingLotKotlin.service.ParkingLotService
import com.dhana.parkingLotKotlin.service.ParkingSpotTypeService
import com.dhana.parkingLotKotlin.service.TicketService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class QueryResolver : GraphQLQueryResolver{

    @Autowired
    private lateinit var parkingLotService: ParkingLotService

    @Autowired
    private lateinit var parkingFloorService: ParkingFloorService

    @Autowired
    private lateinit var parkingSpotTypeService: ParkingSpotTypeService

    @Autowired
    private lateinit var ticketService: TicketService

    fun getParkingLot(id : String): ParkingLot? {
        return  parkingLotService.getById(id)
    }

    fun getAllParkingLot(): Set<ParkingLot?>? {
        return parkingLotService.getAll()
    }

    fun getParkingFloor(id :Int):ParkingFloor?{
        return parkingFloorService.getById(id)
    }

    fun getAllParkingFloor() : Set<ParkingFloor?>? {
        return parkingFloorService.getAll()
    }

    fun getParkingSpotType(parkingSpotType: String) :ParkingSpotType?{
        return parkingSpotTypeService.getById(parkingSpotType);
    }

    fun getAllParkingSpotType() : Set<ParkingSpotType?>?{
        return parkingSpotTypeService.getAll()
    }

    fun getTicket(ticketId :Int) : Ticket?{
        return ticketService.getbyId(ticketId)
    }
}