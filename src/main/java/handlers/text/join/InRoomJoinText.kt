package handlers.text.join

import handlers.text.TextHandler
import handlers.update
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class InRoomJoinText : TextHandler {
    override fun handle(): List<SendMessage> {
        return listOf(SendMessage().setText("Вы уже находитесь в комнате. Для выхода введите /leave").setReplyToMessageId(update.message.messageId))
    }
}