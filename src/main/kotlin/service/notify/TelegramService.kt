package service.notify

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import model.notify.TelegramMessage
import mu.KotlinLogging
import service.api.NotifyService
import java.lang.System.getenv

class TelegramService : NotifyService {

    private val logger = KotlinLogging.logger{}

    companion object {
        var TG_CHAT_ID: String = getenv("TG_CHAT_ID")
        var TG_URL: String = getenv("TG_URL")
    }

    override suspend fun sendMessage(text: String, parseMode: String) {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                gson()
            }
        }

        logger.info { "POST Request to Telegram API. Sending message to: " + TG_CHAT_ID }

        val response: HttpResponse =
            client.post(urlString = TG_URL) {
                contentType(ContentType.Application.Json)
                setBody(TelegramMessage(TG_CHAT_ID, text, parseMode))
            }

        logger.info { "Response from Telegram API: " + response.status.value}
    }

}