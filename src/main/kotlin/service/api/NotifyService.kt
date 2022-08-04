package service.api

interface NotifyService {
    suspend fun sendMessage(text: String, parseMode: String)
}