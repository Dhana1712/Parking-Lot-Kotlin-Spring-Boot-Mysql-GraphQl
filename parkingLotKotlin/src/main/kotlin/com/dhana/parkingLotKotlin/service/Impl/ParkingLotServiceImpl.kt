package com.dhana.parkingLotKotlin.service.Impl

import com.dhana.parkingLotKotlin.entity.ParkingLot
import com.dhana.parkingLotKotlin.repositary.ParkingLotRepo
import com.dhana.parkingLotKotlin.service.ParkingLotService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional
@Service
class ParkingLotServiceImpl : ParkingLotService {
    @Autowired
    private lateinit var parkingLotRepo: ParkingLotRepo

    @Transactional
    override fun getAll(): Set<ParkingLot?>? {
        return parkingLotRepo.findAllBy()
    }

    @Transactional
    override fun getById(id: String?): ParkingLot? {
        return id?.let { parkingLotRepo.findById(it).get() }
    }
    @Transactional
    override fun create(parkingLot: ParkingLot): ParkingLot? {
        return parkingLotRepo.save(parkingLot)
    }
    @Transactional
    override fun delete(id: String?) {
        id?.let { parkingLotRepo.deleteById(it) }
    }
}