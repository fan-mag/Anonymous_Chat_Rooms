package handlers.text.join

import executors.AnonymousRoomsRunner.Companion.broadcast
import handlers.HandlerRouter.routeTo
import handlers.text.TextHandler
import handlers.text.Texts.System.INCORRECT_JOIN
import handlers.text.Texts.System.IN_ROOM_JOIN
import handlers.update
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import properties.RoomProperty.MAX_ROOM_CAPACITY
import properties.RoomProperty.MIN_ROOM_CAPACITY

class JoinText : TextHandler {

    override fun handle(): List<SendMessage> {
        val person = space.person(update.message.from)

        val argument = update.message.text.replaceFirst("/join ", "").split(" ").first()
        if (!argument.matches("^\\d+$".toRegex()) || argument.toInt() < MIN_ROOM_CAPACITY || argument.toInt() > MAX_ROOM_CAPACITY)
            return routeTo(INCORRECT_JOIN)

        val capacity = argument.toInt()
        val room = space.availableRoom(capacity)
        if (!room.addPerson(person)) return routeTo(IN_ROOM_JOIN)
        broadcast(SendMessage().setText("В комнату входит ${person.currentName}"), room.getOtherPersons(person))
        return listOf(SendMessage().setText("""
                                                     Вы вошли в комнату ${room.hashCode()}.
                                                     Текущее количество участников: ${room.persons.size}
                                                     Максимальное количество участников: ${room.capacity}
                                                     Ваше имя в данной комнате: ${person.currentName}
                                                 """.trimIndent()).setReplyToMessageId(update.message.messageId)
        )
    }
}