package com.dhana.parkingLotKotlin.service.Impl

import com.dhana.parkingLotKotlin.Enum.ElectricStatus
import com.dhana.parkingLotKotlin.entity.ElectricBill
import com.dhana.parkingLotKotlin.entity.Ticket
import com.dhana.parkingLotKotlin.repositary.ElectricBillRepo
import com.dhana.parkingLotKotlin.service.ElectricBillService
import com.dhana.parkingLotKotlin.service.TicketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ElectricBillServiceImpl : ElectricBillService{

    @Autowired
    private lateinit var electricBillRepo : ElectricBillRepo

    @Autowired
    private lateinit var ticketService: TicketService

    @Transactional
    override fun powerOn(ticketId: Int?): ElectricBill? {
        var ticket : Ticket? = ticketId?.let { ticketService.getbyId(it) }
        var electricBill : ElectricBill = ElectricBill()
        electricBill.status=ElectricStatus.POWERON
        if (ticket != null) {
            ticket.electricBill =electricBill
        }else{
            throw RuntimeException("the ticket not found")
        }
        electricBill=electricBillRepo.save(electricBill)
        ticketService.update(ticket)
        return electricBill
    }

    @Transactional
    override fun getById(id: Int?): ElectricBill? {
        return id?.let { electricBillRepo.getById(it) }
    }

    @Transactional
    override fun powerOff(powerOffInput: Map<String?, String?>?): ElectricBill? {
        var ticket= powerOffInput?.get("ticketId")?.let { ticketService.getbyId(it.toInt()) }
        var electricBill : ElectricBill? = ticket?.electricBill
        if(electricBill!=null) {
            electricBill.status = ElectricStatus.POWEROFF
            electricBill.energyConsumptionCost = powerOffInput?.get("energyConsumptionCost")?.toFloat()
            return electricBillRepo.save(electricBill)
        }
        return throw RuntimeException("ElectricBill not fount for this ticket")
    }

}