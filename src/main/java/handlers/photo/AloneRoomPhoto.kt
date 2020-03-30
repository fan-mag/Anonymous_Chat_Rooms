package handlers.photo

import handlers.update
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class AloneRoomPhoto : PhotoHandler {
    override fun handle(): List<SendMessage> {
        return listOf(
                SendMessage().setReplyToMessageId(update.message.messageId).setText("В данной комнате больше никого нет\nНеобходимо подождать собеседников")
        )
    }
}