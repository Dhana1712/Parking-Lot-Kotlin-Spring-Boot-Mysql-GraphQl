package com.dhana;

import com.dhana.parkingLotKotlin.entity.Ticket;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class Accounts {
    public float hours(LocalDateTime from, LocalDateTime to) {
        float hours= ChronoUnit.MINUTES.between((Temporal)from,(Temporal)to)/(float)60;
        float hou=ChronoUnit.MINUTES.between((Temporal) from,(Temporal) to);
        return Float.valueOf(new DecimalFormat("#.#").format(hours));
    }


    public float fare(float parkingHours, @Nullable Integer farePerHour) {
        float fare=parkingHours*farePerHour;
        return Float.valueOf(new DecimalFormat("#.##").format(fare));
    }
}
