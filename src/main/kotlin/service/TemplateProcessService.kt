package service

import org.apache.commons.text.StringSubstitutor
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
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

        val loader = javaClass.classLoader
        val file = File(loader.getResource(filePath).file)
        val inputStream = FileInputStream(file);

        val templateText: String = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            .lines()
            .collect(Collectors.joining("\n"))

        return StringSubstitutor(valuesMap).replace(templateText)
    }

    fun formatTimestamp(timestamp: Instant): String {
        val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
            .withLocale(Locale.UK)
            .withZone(ZoneId.systemDefault())

        return formatter.format(timestamp)
    }


}