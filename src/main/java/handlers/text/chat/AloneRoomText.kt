package handlers.text.chat

import handlers.text.TextHandler
import handlers.update
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class AloneRoomText:TextHandler {
    override fun handle(): List<SendMessage> {
        return listOf(
                SendMessage()
                        .setReplyToMessageId(update.message.messageId)
                        .setText("В данной комнате больше никого нет\nНеобходимо подождать собеседников")
        )
    }
}