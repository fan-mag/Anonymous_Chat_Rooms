package handlers.text.leave

import handlers.text.TextHandler
import handlers.update
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class OutRoomLeaveText : TextHandler {

    override fun handle(): List<SendMessage> {
        return listOf(SendMessage().setText("Вы не находитесь в комнате").setReplyToMessageId(update.message.messageId))
    }

}