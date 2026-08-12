package xyz.hyderhadi.personaljournal.ui.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


    @RequiresApi(Build.VERSION_CODES.O)
    fun formatTimeStamp(timeStamp: Long): String {
        return Instant
            .ofEpochMilli(timeStamp)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("dd MM yyyy"))
    }