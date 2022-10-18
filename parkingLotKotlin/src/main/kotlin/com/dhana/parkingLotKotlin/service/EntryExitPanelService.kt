package com.dhana.parkingLotKotlin.service

import com.dhana.parkingLotKotlin.entity.EntryExitPanel

interface EntryExitPanelService {
    fun getAll(): Set<EntryExitPanel?>?
    fun getById(id: Int?): EntryExitPanel?
    fun create(panelInput: Map<String?, Any?>?): EntryExitPanel?
    fun delete(panelInput: Map<String?, Any?>?)
}