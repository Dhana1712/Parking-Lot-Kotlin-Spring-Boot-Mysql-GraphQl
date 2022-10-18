package com.dhana.parkingLotKotlin.service.Impl

import com.dhana.Accounts
import com.dhana.parkingLotKotlin.Enum.ParkingSpotQuota
import com.dhana.parkingLotKotlin.Enum.ParkingSpotStatus
import com.dhana.parkingLotKotlin.entity.ParkingSpot
import com.dhana.parkingLotKotlin.entity.Ticket
import com.dhana.parkingLotKotlin.repositary.TicketRepo
import com.dhana.parkingLotKotlin.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.transaction.Transactional
@Service
class TicketServiceImpl : TicketService {

    @Autowired
    private lateinit var parkingSpotService: ParkingSpotService

    @Autowired
    private lateinit var parkingLotService: ParkingLotService
    @Autowired
    private lateinit var ticketRepo : TicketRepo

    @Autowired
    private lateinit var parkingSpotTypeService: ParkingSpotTypeService

    @Autowired
    private lateinit var entryExitPanelService: EntryExitPanelService

    @Transactional
    override fun getbyId(id: Int): Ticket? {
        return ticketRepo.findById(id).get()
    }

    @Transactional
    override fun getAll(): Set<Ticket?>? {
        return null
    }
    @Transactional
    override fun park(parkInput: Map<String?, Any?>): Ticket? {
        var ticket=Ticket()
        ticket.vehicleNumber=parkInput?.get("vehicleNo").toString()
        var entryPanel =entryExitPanelService.getById(parkInput?.get("entryPanelId").toString().toInt())
        if (entryPanel!=null)
            ticket.entryPanel=entryPanel
        else
            throw RuntimeException("EntryPanel not found")

        var parkingLot=parkingLotService.getById(parkInput.get("parkingLotId").toString())
        if (parkingLot!=null)
            ticket.parkingLot=parkingLot
        else
            throw RuntimeException("Parking Lot not found")
        var parkingSpotType=parkingSpotTypeService.getById(parkInput.get("vehicleType") as String)
        if (parkingSpotType != null)
            ticket.vehicleType=parkingSpotType.parkingSpotType
        else
            throw RuntimeException("Parking Spot not available for this vehicle type")
        var parkingSpot=parkingSpotService.getSpot(parkingSpotType, ParkingSpotQuota.valueOf(parkInput.get("parkingSpotQuota") as String))
        if(parkingSpot!=null){
            ticket.parkingSpot=parkingSpot
            parkingSpot.status=ParkingSpotStatus.OCCUPIED
            parkingSpotService.update(parkingSpot)
        }else
            throw RuntimeException("ParkingSpot not available")
        ticket.entryTime= LocalDateTime.now()
        return ticketRepo.save(ticket)

    }
    @Transactional
    override fun unPark(unParkInput: Map<String?, Int?>?): Ticket? {
        var ticket = unParkInput?.get("ticketId")?.let { this.getbyId(it) }
        var exitPanel =entryExitPanelService.getById(unParkInput?.get("exitPanelId"));
        if(ticket!=null) {
            if (exitPanel != null)
                ticket.exitPanel = exitPanel
            else
                throw RuntimeException("ExitPanel Not found")
            ticket.exitTime = LocalDateTime.now()
            ticket.parkingHours=Accounts().hours(ticket.exitTime,ticket.exitTime)
            ticket.parkingFare=Accounts().fare(ticket.parkingHours!!,
                parkingSpotTypeService.getById(ticket.vehicleType)?.farePerHour
            )
            var parkingSpot :ParkingSpot= ticket.parkingSpot!!
            parkingSpot.status=ParkingSpotStatus.AVAILABLE
            parkingSpotService.update(parkingSpot)
            return ticketRepo.save(ticket)
        }else{
            throw RuntimeException("Ticket not found")
        }
    }
    @Transactional
    override fun hours(from: LocalDateTime?, to: LocalDateTime?): Float {
        var hours=ChronoUnit.MINUTES.between(from,to)/(60.toFloat())
        var hou=ChronoUnit.MINUTES.between(from,to)
        print("/n/n$hou")
        return hours
    }
    @Transactional
    override fun fare(totalHours: Float, farePerHour: Int?): Float {
        var fare=totalHours* farePerHour!!
        return fare;
    }
    @Transactional
    override fun update(ticket: Ticket): Ticket? {
        return  ticketRepo.save(ticket)
    }
}