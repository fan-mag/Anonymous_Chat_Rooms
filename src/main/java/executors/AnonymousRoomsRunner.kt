package executors

import handlers.HandlerRouter
import model.Person
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.Update
import properties.ApiEndpointsProperty

class AnonymousRoomsRunner : TelegramLongPollingBot() {
    val botName = "Anonymous Chat Rooms"

    override fun onUpdateReceived(update: Update) {
        if (update.message.hasText()) {
            HandlerRouter.handleTextUpdate(update).forEach {
                it.chatId = "${update.message.chatId}"
                execute(it)
            }
        }
        if (update.message.hasPhoto()) {
            HandlerRouter.handlePhotoUpdate(update).forEach {
                it.chatId = "${update.message.chatId}"
                execute(it)
            }
        }
    }


    override fun getBotUsername(): String {
        return botName
    }


    override fun getBotToken(): String {
        return ApiEndpointsProperty.BOT_TOKEN.toString()
    }

    companion object {

        lateinit var instance: AnonymousRoomsRunner

        @JvmStatic
        fun main(args: Array<String>) {
            ApiContextInitializer.init() // Инициализируем апи
            instance = AnonymousRoomsRunner()
            StateSaver.loadState()
            TelegramBotsApi().registerBot(instance).start()
        }

        fun sendBroadcastMessage(message: SendMessage, persons: List<Person>) {
            persons.forEach {
                message.chatId = "${it.user.id}"
                instance.execute(message)
            }
        }

        fun sendBroadcastPhoto(photo: SendPhoto, persons: List<Person>) {
            persons.forEach {
                photo.chatId = "${it.user.id}"
                instance.execute(photo)
            }
        }
    }
}