package job

import kotlinx.coroutines.*
import service.HardwareFetchService
import service.TemplateProcessService
import service.api.NotifyService
import java.time.Instant
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class NotificationScheduler(val service: NotifyService, val interval: Long, val initDelay: Long?) : CoroutineScope {
    private val job = Job()

    private val singleThreadExecutor = Executors.newSingleThreadExecutor()

    override val coroutineContext: CoroutineContext
        get() = job + singleThreadExecutor.asCoroutineDispatcher()


    fun stop() {
        job.cancel()
        singleThreadExecutor.shutdown()
    }

    fun start() = launch {
        initDelay?.let {
            delay(it)
        }
        while (isActive) {

            val hardware = HardwareFetchService().fetch();
            val timestamp: String = TemplateProcessService().formatTimestamp(Instant.now())
            val valuesMap: HashMap<String, String> = HashMap<String, String>()
            valuesMap.put("cpuUsage", hardware.cpuUsage.toString())
            valuesMap.put("memoryUsage", hardware.memoryUsage.toString())
            valuesMap.put("uptime", hardware.uptime)
            valuesMap.put("totalMemory", hardware.totalMemory.toString())
            valuesMap.put("availableMemory", hardware.availableMemory.toString())
            valuesMap.put("timestamp", timestamp)

            val message = TemplateProcessService().process(valuesMap, "templates/reportMessage")

            if (message != null) {
                service.sendMessage(message, "HTML")
            }
            delay(interval)
        }
        println("Coroutine done")
    }
}