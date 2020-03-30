package handlers.text.join

import handlers.text.TextHandler
import handlers.update
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import properties.RoomProperty.MAX_ROOM_CAPACITY
import properties.RoomProperty.MIN_ROOM_CAPACITY

class IncorrectJoinText : TextHandler {
    override fun handle(): List<SendMessage> {
        return listOf(
                SendMessage().setText("Неверная команда. Используйте /join N, где N - от $MIN_ROOM_CAPACITY до $MAX_ROOM_CAPACITY").setReplyToMessageId(update.message.messageId)
        )
    }
}