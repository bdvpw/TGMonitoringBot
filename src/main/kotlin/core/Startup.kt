package core

import com.andreapivetta.kolor.Color
import com.andreapivetta.kolor.Kolor
import job.NotificationScheduler
import service.notify.TelegramService
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.stream.Collector
import java.util.stream.Collectors

class Startup() {


    fun run(notifyInterval: Long) {
        println(logo())
        NotificationScheduler(TelegramService(), notifyInterval, 0).start()
    }

    private fun logo(): String {

        val loader = javaClass.classLoader
        val file = File(loader.getResource("startup/logo").file)
        val inputStream = FileInputStream(file);

        val text: String = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            .lines()
            .collect(Collectors.joining("\n"))

        return Kolor.foreground(text, Color.YELLOW)
    }

}