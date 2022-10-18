package com.dhana.parkingLotKotlin.service.Impl

import com.dhana.parkingLotKotlin.Enum.ParkingSpotQuota
import com.dhana.parkingLotKotlin.Enum.ParkingSpotStatus
import com.dhana.parkingLotKotlin.entity.ParkingFloor
import com.dhana.parkingLotKotlin.entity.ParkingSpot
import com.dhana.parkingLotKotlin.entity.ParkingSpotType
import com.dhana.parkingLotKotlin.repositary.ParkingSpotRepo
import com.dhana.parkingLotKotlin.service.ParkingFloorService
import com.dhana.parkingLotKotlin.service.ParkingSpotService
import com.dhana.parkingLotKotlin.service.ParkingSpotTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional
@Service
class ParkingSpotServiceImpl : ParkingSpotService {
    @Autowired
    private lateinit var parkingSpotRepo: ParkingSpotRepo
    @Autowired
    private lateinit var parkingFloorService: ParkingFloorService
    @Autowired
    private lateinit var parkingSpotTypeService: ParkingSpotTypeService

    @Transactional
    override fun getAll(): Set<ParkingSpot?>? {
        return parkingSpotRepo.findAllBy()
    }
    @Transactional
    override fun getById(id: Int): ParkingSpot? {
        return  parkingSpotRepo.findById(id).get()
    }
    @Transactional
    override fun create(parkingSpotInput: Map<String?, Any?>?): ParkingSpot? {
        var parkingSpot = ParkingSpot()
        parkingSpot.parkingSpotNo= parkingSpotInput?.get("parkingSpotNo").toString().toInt()
        var parkingFloor = parkingFloorService.getByFloorNoAndParkingLotId(parkingSpotInput?.get("parkingFloorNo").toString().toInt(),parkingSpotInput?.get("parkingLotId").toString())
        if (parkingFloor!=null)
            parkingSpot.parkingFloor=parkingFloor
        else
            throw RuntimeException("ParkingFloor not found")
        var parkingSpotType=parkingSpotTypeService.getById(parkingSpotInput?.get("parkingSpotType") as String)
        if (parkingSpotType!=null)
            parkingSpot.parkingSpotType=parkingSpotType
        else
            throw RuntimeException("ParkingSpot type not found")
        parkingSpot.parkingSpotQuota= ParkingSpotQuota.valueOf(parkingSpotInput?.get("parkingSpotQuota") as String)
        parkingSpot.status=ParkingSpotStatus.AVAILABLE
        if (parkingSpotRepo.findByParkingSpotNoAndParkingSpotTypeAndParkingFloorAndParkingSpotQuota(parkingSpot.parkingSpotNo!!,parkingSpot.parkingSpotType,parkingSpot.parkingFloor,parkingSpot.parkingSpotQuota)==null)
            return  parkingSpotRepo.save(parkingSpot)
        else
            throw RuntimeException("ParkingSpot Already Exists")

    }
    @Transactional
    override fun delete(parkingSpotInput: Map<String?, Any?>?) {
        var parkingSpot = ParkingSpot()
        parkingSpot.parkingSpotNo= parkingSpotInput?.get("parkingSpotNo") as Int?
        var parkingFloor = parkingFloorService.getByFloorNoAndParkingLotId(parkingSpotInput?.get("parkingFloorNo") as Int?,parkingSpotInput?.get("parkingLotId").toString())
        if (parkingFloor!=null)
            parkingSpot.parkingFloor=parkingFloor
        else
            throw RuntimeException("ParkingFloor not found")
        var parkingSpotType=parkingSpotTypeService.getById(parkingSpotInput?.get("parkingSpotType") as String)
        if (parkingSpotType!=null)
            parkingSpot.parkingSpotType=parkingSpotType
        else
            throw RuntimeException("ParkingSpot type not found")
        parkingSpot.parkingSpotQuota= ParkingSpotQuota.valueOf(parkingSpotInput?.get("parkingSpotQuota") as String)
        var parkingSpot1 : ParkingSpot? = parkingSpotRepo.findByParkingSpotNoAndParkingSpotTypeAndParkingFloorAndParkingSpotQuota(parkingSpot.parkingSpotNo!!,parkingSpot.parkingSpotType,parkingSpot.parkingFloor,parkingSpot.parkingSpotQuota)
        if (parkingSpot1!=null)
            return  parkingSpotRepo.deleteById(parkingSpot.id!!)
        else
            throw RuntimeException("ParkingSpot not Exists")

    }
    @Transactional
    override fun getAvailableSpots(parkingSpotType: ParkingSpotType?, parkingSpotQuota: ParkingSpotQuota?, available: ParkingSpotStatus?): List<ParkingSpot?>? {
        var parkingSpots : List<ParkingSpot?>?=parkingSpotRepo.findByParkingSpotTypeAndParkingSpotQuotaAndStatusOrderByParkingSpotNoAsc(parkingSpotType,parkingSpotQuota,available)
        return parkingSpots
    }
    @Transactional
    override fun getSpot(parkingSpotType: ParkingSpotType?,parkingSpotQuota: ParkingSpotQuota?): ParkingSpot? {
        var parkingSpots : List<ParkingSpot?>?=this.getAvailableSpots(parkingSpotType,parkingSpotQuota,ParkingSpotStatus.AVAILABLE)
        if (parkingSpots.isNullOrEmpty()){
            return null
        }
        return parkingSpots.get(0)
    }
    @Transactional
    override fun update(parkingSpot: ParkingSpot): ParkingSpot? {
        return parkingSpotRepo.save(parkingSpot)
    }
}