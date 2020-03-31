package handlers.text.chat

import executors.AnonymousRoomsRunner.Companion.broadcast
import handlers.HandlerRouter.routeTo
import handlers.text.TextHandler
import handlers.text.Texts.System.ALONE_ROOM
import handlers.update
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class ChatRoomText : TextHandler {
    override fun handle(): List<SendMessage> {
        val person = space.person(update.message.from)
        val text = update.message.text
        if (person.room.persons.size == 1) return routeTo(ALONE_ROOM)
        person.room.log("${person.currentName}: $text")

        broadcast(
                message = SendMessage().setText("${person.currentName}: $text"),
                persons = person.room.getOtherPersons(person)
        )
        return emptyList()
    }
}