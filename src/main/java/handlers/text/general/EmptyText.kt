package handlers.text.general

import handlers.text.TextHandler
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class EmptyText : TextHandler {
    override fun handle(): List<SendMessage> {
        return emptyList()
    }
}