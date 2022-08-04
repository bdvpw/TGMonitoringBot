package service

import com.google.common.io.Resources
import org.apache.commons.text.StringSubstitutor
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.HashMap

class TemplateProcessService {


    fun process(valuesMap: HashMap<String, String>, filePath: String): String? {

        val text: String = getResourceAsText(filePath).toString()
        return StringSubstitutor(valuesMap).replace(text)
    }

    fun formatTimestamp(timestamp: Instant): String {
        val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
            .withLocale(Locale.UK)
            .withZone(ZoneId.systemDefault())

        return formatter.format(timestamp)
    }

    private fun getResourceAsText(path: String): String? =
        object {}.javaClass.getResource(path)?.readText()


}