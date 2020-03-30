package handlers.text

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class NoRoomText : TextHandler {

    override fun handle(update: Update): List<SendMessage> {
        return listOf(
                SendMessage()
                        .setReplyToMessageId(update.message.messageId)
                        .setText("Вы не находитесь в чат-комнате. Для входа введите /join N, где N - макс. кол-во участников в комнате"))
    }

}