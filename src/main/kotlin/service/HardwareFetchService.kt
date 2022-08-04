package service

import model.Hardware
import oshi.SystemInfo
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.math.roundToInt

class HardwareFetchService {

    companion object {
        const val MEGABYTE = 1024L * 1024L;
        var systemInfo: SystemInfo = SystemInfo();
    }

    public fun fetch(): Hardware {

        val decimalFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.ENGLISH))
        decimalFormat.roundingMode = RoundingMode.CEILING

        val cpuUsage: Double = decimalFormat
            .format(systemInfo.hardware.processor.getSystemCpuLoad(1000) * 100)
            .toDouble()

        val memoryUsage: Double = memoryUsage()

            return Hardware(cpuUsage, memoryUsage)
    }

    private fun bytesToMegabytes(bytes: Long): Long {
        return bytes / MEGABYTE;
    }

    public fun memoryUsage(): Double {
        val totalMemory: Long = bytesToMegabytes(systemInfo.hardware.memory.total)
        val availableMemory: Long = bytesToMegabytes(systemInfo.hardware.memory.available)
        return 100.0 - (availableMemory.toDouble().div(totalMemory.toDouble()) * 100).roundToInt();
    }

}