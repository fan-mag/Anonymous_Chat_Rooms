package handlers.text

import executors.AnonymousRoomsRunner.Companion.broadcast
import handlers.update
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class ChatRoomText : TextHandler {
    override fun handle(): List<SendMessage> {
        val person = space.person(update.message.from)
        val text = update.message.text
        person.room.log("${person.currentName}: $text")
        broadcast(
                message = SendMessage().setText("${person.currentName}: $text"),
                persons = person.room.getOtherPersons(person)
        )
        if (person.room.persons.size == 1) {
            return listOf(
                    SendMessage().setReplyToMessageId(update.message.messageId).setText("В данной комнате больше никого нет\nНеобходимо подождать собеседников")
            )
        }
        return emptyList()
    }
}