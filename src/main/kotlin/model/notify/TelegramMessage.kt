package model.notify

import com.google.gson.annotations.SerializedName

data class TelegramMessage(
    @SerializedName(value = "chat_id")
    val chatId: String,
    val text: String,
    @SerializedName(value = "parse_mode")
    val parseMode: String
)
