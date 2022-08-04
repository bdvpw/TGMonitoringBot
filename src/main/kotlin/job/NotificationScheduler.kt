package job

import kotlinx.coroutines.*
import service.HardwareFetchService
import service.api.NotifyService
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
            service.sendMessage(HardwareFetchService().fetch().toString(), "HTML")
            delay(interval)
        }
        println("Coroutine done")
    }
}