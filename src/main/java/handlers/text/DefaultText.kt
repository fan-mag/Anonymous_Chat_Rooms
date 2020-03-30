package handlers.text

import handlers.update
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import properties.Smiles.FUCK_BLACK
import java.util.*

class DefaultText : TextHandler {

    override fun handle(): List<SendMessage> {
        val replies: MutableList<SendMessage> = ArrayList()
        replies.add(SendMessage().setReplyToMessageId(update.message.messageId).setText(FUCK_BLACK.toString() + " Нет обработчика для данного сообщения" + FUCK_BLACK + System.lineSeparator() + "Введите \"/help\" для помощи"))
        return replies
    }

}