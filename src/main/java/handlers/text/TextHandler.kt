package handlers.text

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

internal interface TextHandler {
    fun handle(update: Update): List<SendMessage>
}