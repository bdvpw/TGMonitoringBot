import service.HardwareFetchService
import service.notify.TelegramService

suspend fun main(args: Array<String>) {
    TelegramService().sendMessage(HardwareFetchService().fetch().toString(), "HTML")
}