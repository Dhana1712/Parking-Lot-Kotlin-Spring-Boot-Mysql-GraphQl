package com.dhana.parkingLotKotlin.service.Impl

import com.dhana.parkingLotKotlin.Enum.PanelType
import com.dhana.parkingLotKotlin.entity.EntryExitPanel
import com.dhana.parkingLotKotlin.entity.ParkingLot
import com.dhana.parkingLotKotlin.repositary.EntryExitPanelRepo
import com.dhana.parkingLotKotlin.service.EntryExitPanelService
import com.dhana.parkingLotKotlin.service.ParkingLotService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import kotlin.toString as toString1
@Service
class EntryExitPanelServiceImpl : EntryExitPanelService{
    @Autowired
    private lateinit var parkingLotService : ParkingLotService
    @Autowired
    private lateinit var entryExitPanelRepo: EntryExitPanelRepo

    @Transactional
    override fun getAll(): Set<EntryExitPanel?>? {
        return entryExitPanelRepo.findAllBy()
    }
    @Transactional
    override fun getById(id: Int?): EntryExitPanel? {
       return id?.let { entryExitPanelRepo.findById(it).get() }
    }
    @Transactional
    override fun create(panelInput: Map<String?, Any?>?): EntryExitPanel? {
        var entryExitPanel : EntryExitPanel = EntryExitPanel()
        var parkingLot : ParkingLot? = parkingLotService.getById(panelInput?.get("parkingLotId")?.toString())
        if(parkingLot !=null)
            entryExitPanel.parkingLot=parkingLot
        else
            throw RuntimeException("ParkingLot not found")

        entryExitPanel.panelName = panelInput?.get("panelName")?.toString()
        entryExitPanel.panelType = panelInput?.get("panelType")?.let { PanelType.valueOf(it.toString()) }
        if(entryExitPanelRepo.findByPanelNameAndPanelTypeAndParkingLot(entryExitPanel.panelName,entryExitPanel.panelType,entryExitPanel.parkingLot)==null)
            return entryExitPanelRepo.save(entryExitPanel)
        else
            throw RuntimeException("Panel already exists");
    }
    @Transactional
    override fun delete(panelInput: Map<String?, Any?>?) {
        var entryExitPanel : EntryExitPanel = EntryExitPanel()
        var parkingLot : ParkingLot? = parkingLotService.getById(panelInput?.get("parkingLotId")?.toString())
        if(parkingLot!=null)
            entryExitPanel.parkingLot=parkingLot
        else
            throw RuntimeException("ParkingLot not found");
        entryExitPanel.panelName =panelInput?.get("panelName")?.toString()
        entryExitPanel.panelType= panelInput?.get("panelType")?.let { PanelType.valueOf(it.toString()) }
        var entryExitPanel1 =entryExitPanelRepo.findByPanelNameAndPanelTypeAndParkingLot(entryExitPanel.panelName,entryExitPanel.panelType,entryExitPanel.parkingLot)
        if (entryExitPanel1!=null)
            entryExitPanelRepo.delete(entryExitPanel1)
        else
            throw  RuntimeException("Panel not exists");
    }
}