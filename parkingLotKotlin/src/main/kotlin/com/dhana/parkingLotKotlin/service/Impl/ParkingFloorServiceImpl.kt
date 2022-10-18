package com.dhana.parkingLotKotlin.service.Impl

import com.dhana.parkingLotKotlin.entity.ParkingFloor
import com.dhana.parkingLotKotlin.repositary.ParkingFloorRepo
import com.dhana.parkingLotKotlin.service.ParkingFloorService
import com.dhana.parkingLotKotlin.service.ParkingLotService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional
@Service
class ParkingFloorServiceImpl : ParkingFloorService {
    @Autowired
    private lateinit var parkingFloorRepo: ParkingFloorRepo
    @Autowired
    private lateinit var parkingLotService: ParkingLotService

    @Transactional
    override fun getAll(): Set<ParkingFloor?>? {
        return parkingFloorRepo.findAllBy()
    }
    @Transactional
    override fun getById(id: Int?): ParkingFloor? {
        return id?.let { parkingFloorRepo.getById(it) }
    }
    @Transactional
    override fun create(parkingFloorInput: Map<String?, String?>): ParkingFloor? {
        val parkingFloor=ParkingFloor()
        val parkingLot=parkingLotService.getById(parkingFloorInput.get("parkingLotId"))
        if(parkingLot!=null)
            parkingFloor.parkingLot=parkingLot
        else
            throw RuntimeException("ParkingLot not exist")
        parkingFloor.floorNo= parkingFloorInput.get("floorNo")?.toInt()
        if (parkingFloorRepo.findByFloorNoAndParkingLotId(parkingFloor.floorNo, parkingFloor.parkingLot!!.id) ==null)
            return  parkingFloorRepo.save(parkingFloor)
        else
            throw RuntimeException("Floor already exist ")



    }
    @Transactional
    override fun delete(parkingFloorInput: Map<String?, String?>) {
        var parkingFloor=ParkingFloor()
        parkingFloor.floorNo= parkingFloorInput.get("floorNo")?.toInt()
        val parkingLot=parkingLotService.getById(parkingFloorInput.get("parkingLotId"))
        if (parkingLot!=null)
            parkingFloor.parkingLot=parkingLot
        else
            throw RuntimeException("ParkingLot not found")
        parkingFloor= parkingFloorRepo.findByFloorNoAndParkingLotId(parkingFloor.floorNo, parkingFloor.parkingLot!!.id)!!
        if (parkingFloor!=null)
            parkingFloorRepo.deleteById(parkingFloor.id!!)
        else
            throw RuntimeException("ParkingFloor not exist")
    }
    @Transactional
    override fun getByFloorNoAndParkingLotId(floorNo: Int?, parkingLotId: String?): ParkingFloor? {
        return  parkingFloorRepo.findByFloorNoAndParkingLotId(floorNo,parkingLotId)
    }
}