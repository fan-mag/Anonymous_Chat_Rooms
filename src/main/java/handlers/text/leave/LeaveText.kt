package handlers.text.leave

import executors.AnonymousRoomsRunner.Companion.broadcast
import handlers.HandlerRouter.routeTo
import handlers.text.TextHandler
import handlers.text.Texts.System.OUT_ROOM_LEAVE
import handlers.update
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class LeaveText : TextHandler {
    override fun handle(): List<SendMessage> {
        val person = space.person(update.message.from)
        if (!person.hasChat) return routeTo(OUT_ROOM_LEAVE)

        val room = person.room
        broadcast(
                message = SendMessage().setText("Пользователь ${person.currentName} выходит из комнаты"),
                persons = room.getOtherPersons(person)
        )
        room.removePerson(person)
        return listOf(SendMessage().setText("Вы вышли из комнаты").setReplyToMessageId(update.message.messageId))
    }


}