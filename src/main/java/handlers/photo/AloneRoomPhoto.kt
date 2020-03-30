package handlers.photo

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class AloneRoomPhoto : PhotoHandler {
    override fun handle(update: Update): List<SendMessage> {
        return listOf(
                SendMessage().setReplyToMessageId(update.message.messageId).setText("В данной комнате больше никого нет\nНеобходимо подождать собеседников")
        )
    }
}