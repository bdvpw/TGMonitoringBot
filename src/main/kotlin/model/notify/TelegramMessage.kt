package model.notify

import com.google.gson.annotations.SerializedName

data class TelegramMessage(
    @SerializedName(value = "chat_id")
    val chatId: String,
    val text: String,
    val parseMode: String
)
