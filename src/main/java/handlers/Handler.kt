package handlers

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

interface Handler {
    fun handle(update: Update): List<SendMessage>
}