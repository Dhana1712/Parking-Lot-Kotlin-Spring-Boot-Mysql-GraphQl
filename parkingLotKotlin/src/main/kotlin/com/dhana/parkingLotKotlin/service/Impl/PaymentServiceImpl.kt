package com.dhana.parkingLotKotlin.service.Impl

import com.dhana.parkingLotKotlin.Enum.ParkingSpotQuota
import com.dhana.parkingLotKotlin.Enum.PaymentMethod
import com.dhana.parkingLotKotlin.Enum.PaymentStatus
import com.dhana.parkingLotKotlin.entity.Payment
import com.dhana.parkingLotKotlin.repositary.PaymentRepo
import com.dhana.parkingLotKotlin.service.PaymentService
import com.dhana.parkingLotKotlin.service.TicketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional
@Service
class PaymentServiceImpl : PaymentService {
    @Autowired
    private lateinit var paymentRepo: PaymentRepo

    @Autowired
    private lateinit var ticketService: TicketService

    @Transactional
    override fun cardPayment(ticketId: Int): Payment? {
        var ticket=ticketService.getbyId(ticketId)
        if(ticket!=null && ticket?.payment==null){
            var payment=Payment()
            payment.method=PaymentMethod.CARD
            payment.ticket=ticket
            if(ticket.parkingSpot?.parkingSpotQuota==ParkingSpotQuota.ELECTRIC){
                payment.amount= ticket.electricBill?.energyConsumptionCost?.let { ticket.parkingFare?.plus(it) }
            }else
                payment.amount=ticket?.parkingFare
            if(cardPayment(payment)){
                payment.status=PaymentStatus.SUCCESS
                payment.paidAt= LocalDateTime.now()
                ticket.payment=payment
                payment=paymentRepo.save(payment)
                ticketService.update(ticket)
                return payment
            }else
                throw RuntimeException("payment failed")
        }else{
            throw RuntimeException("payment already compleated")
        }
    }

    @Transactional
    override fun cashPayment(ticketId: Int): Payment? {
        var ticket=ticketService.getbyId(ticketId)
        if(ticket!=null&&ticket.payment==null ){
            var payment=Payment()
            payment.method=PaymentMethod.CASH
            payment.paidAt= LocalDateTime.now()
            payment.ticket=ticket
            payment.status=PaymentStatus.SUCCESS
            if(ticket.parkingSpot?.parkingSpotQuota == ParkingSpotQuota.ELECTRIC) {
                payment.amount = ticket.electricBill?.energyConsumptionCost?.let { ticket.parkingFare?.plus(it) }
            }else
                payment.amount=ticket?.parkingFare
            ticket.payment=payment
            payment=paymentRepo.save(payment)
            ticketService.update(ticket)
            return payment
        }else{
            throw RuntimeException("payment already compleated")
        }
    }

    @Transactional
    fun cardPayment(payment: Payment): Boolean {
        return true
    }
}