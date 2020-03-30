package handlers

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

interface Handler {
    fun handle(): List<SendMessage>
}