package handlers.text

import executors.AnonymousRoomsRunner.Companion.sendBroadcastMessage
import executors.StateSaver.saveState
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class LeaveText : TextHandler {
    override fun handle(update: Update): List<SendMessage> {
        val person = space.person(update.message.from)
        return if (person.hasChat) {
            val room = person.room
            sendBroadcastMessage(
                    SendMessage().setText("Пользователь ${person.currentName} выходит из комнаты"), room.getOtherPersons(person)
            )
            room.removePerson(person)
            room.log("LEFT user ${person.userToString()} as ${person.currentName}")
            if (room.isEmpty()) {
                room.log("CLOSE room")
                space.rooms.remove(room)
            }
            saveState()
            listOf(
                    SendMessage()
                            .setText("Вы вышли из комнаты"))
        } else listOf(
                SendMessage()
                        .setText("Вы не находитесь в комнате")
        )
    }
}