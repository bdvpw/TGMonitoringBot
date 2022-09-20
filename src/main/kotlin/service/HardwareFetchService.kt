package service

import model.Hardware
import mu.KotlinLogging
import oshi.SystemInfo
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

class HardwareFetchService {

    private val logger = KotlinLogging.logger{}

    companion object {
        const val MEGABYTE = 1024L * 1024L;
        var systemInfo: SystemInfo = SystemInfo();
    }

    fun fetch(): Hardware {

        logger.info { "Fetching information about hardware..." }

        val decimalFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.ENGLISH))
        decimalFormat.roundingMode = RoundingMode.CEILING

        val cpuUsage: Double = decimalFormat
            .format(systemInfo.hardware.processor.getSystemCpuLoad(1000) * 100)
            .toDouble()

        val totalMemory: Long = bytesToMegabytes(systemInfo.hardware.memory.total)
        val availableMemory: Long = bytesToMegabytes(systemInfo.hardware.memory.available)

        val memoryUsage: Double = memoryUsage(totalMemory, availableMemory)
        val uptimeSeconds: Long = systemInfo.operatingSystem.systemUptime

        val uptime: String = String.format("%02d:%02d:%02d",
            TimeUnit.SECONDS.toHours(uptimeSeconds),
            TimeUnit.SECONDS.toMinutes(uptimeSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(uptimeSeconds)),
            TimeUnit.SECONDS.toSeconds(uptimeSeconds - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(uptimeSeconds))))

        return Hardware(cpuUsage, memoryUsage, totalMemory, availableMemory, uptime)
    }

    private fun bytesToMegabytes(bytes: Long): Long {
        return bytes / MEGABYTE;
    }

    private fun memoryUsage(totalMemory: Long, availableMemory: Long): Double {
        return 100.0 - (availableMemory.toDouble().div(totalMemory.toDouble()) * 100).roundToInt();
    }


}