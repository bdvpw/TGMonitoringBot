package model

import oshi.driver.unix.aix.Uptime

data class Hardware(
    var cpuUsage: Double,
    var memoryUsage: Double,
    var totalMemory: Long,
    var availableMemory: Long,
    var uptime: String,
)
