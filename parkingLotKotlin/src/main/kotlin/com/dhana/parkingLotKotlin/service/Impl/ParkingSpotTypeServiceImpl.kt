package com.dhana.parkingLotKotlin.service.Impl

import com.dhana.parkingLotKotlin.entity.ParkingSpotType
import com.dhana.parkingLotKotlin.repositary.ParkingSpotTypeRepo
import com.dhana.parkingLotKotlin.service.ParkingSpotTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional
@Service
class ParkingSpotTypeServiceImpl : ParkingSpotTypeService{
    @Autowired
    private lateinit var parkingSpotTypeRepo: ParkingSpotTypeRepo


    @Transactional
    override fun getAll(): Set<ParkingSpotType?>? {
        return parkingSpotTypeRepo.findAllBy();
    }

    @Transactional
    override fun getById(id: String?): ParkingSpotType? {
        return id?.let { parkingSpotTypeRepo.findById(it).get() }
    }

    @Transactional
    override fun create(parkingSpotType: ParkingSpotType): ParkingSpotType? {
        return parkingSpotTypeRepo.save(parkingSpotType)
    }

    @Transactional
    override fun delete(id: String?) {
        id?.let { parkingSpotTypeRepo.deleteById(it) }
    }
}