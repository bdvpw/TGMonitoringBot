package core

import com.andreapivetta.kolor.Color
import com.andreapivetta.kolor.Kolor
import com.google.common.io.Resources
import job.NotificationScheduler
import service.notify.TelegramService
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.stream.Collector
import java.util.stream.Collectors

class Startup() {

    fun run(notifyInterval: Long) {
        println(logo())
        NotificationScheduler(TelegramService(), notifyInterval, 0).start()
    }

    private fun logo(): String {
        val text: String = getResourceAsText("/startup/logo").toString()
        return Kolor.foreground(text, Color.YELLOW)
    }

    private fun getResourceAsText(path: String): String? =
        object {}.javaClass.getResource(path)?.readText()

}