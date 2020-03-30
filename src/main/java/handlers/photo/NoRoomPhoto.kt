package handlers.photo

import handlers.update
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class NoRoomPhoto : PhotoHandler {
    override fun handle(): List<SendMessage> {
        return listOf(
                SendMessage()
                        .setReplyToMessageId(update.message.messageId)
                        .setText("Вы не находитесь в чат-комнате. Для входа введите /join N, где N - макс. кол-во участников в комнате"))
    }
}