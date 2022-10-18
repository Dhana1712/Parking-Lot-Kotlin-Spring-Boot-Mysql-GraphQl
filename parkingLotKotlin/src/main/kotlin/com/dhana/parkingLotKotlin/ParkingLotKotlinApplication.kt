package com.dhana.parkingLotKotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParkingLotKotlinApplication

fun main(args: Array<String>) {
	System.setProperty("spring.devtools.restart.enabled", "true")
	runApplication<ParkingLotKotlinApplication>(*args)
}
