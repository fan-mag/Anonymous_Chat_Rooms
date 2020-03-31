package handlers.text.roominfo

import handlers.HandlerRouter.routeTo
import handlers.text.TextHandler
import handlers.text.Texts.System.NO_ROOM
import handlers.update
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class RoomInfoText : TextHandler {

    override fun handle(): List<SendMessage> {
        val person = space.person(update.message.from)
        if (!person.hasChat) return routeTo(NO_ROOM)

        val sb1 = StringBuilder("В комнате ${person.room.hashCode()} в данный момент находятся:\n${person.currentName} (Вы)\n")
        person.room.persons.filterNot { it == person }.forEach { sb1.append("${it.currentName}\n") }
        return listOf(SendMessage().setText(sb1.toString()).setReplyToMessageId(update.message.messageId))
    }
}