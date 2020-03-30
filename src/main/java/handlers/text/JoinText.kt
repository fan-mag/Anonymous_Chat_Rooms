package handlers.text

import executors.AnonymousRoomsRunner.Companion.sendBroadcastMessage
import executors.StateSaver.saveState
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import properties.RoomProperty.MAX_ROOM_CAPACITY
import properties.RoomProperty.MIN_ROOM_CAPACITY

class JoinText : TextHandler {

    override fun handle(update: Update): List<SendMessage> {
        val person = space.person(update.message.from)
        try {
            val capacity = update.message.text.replaceFirst("/join ", "").split(" ").first().toInt()
            if (capacity < MIN_ROOM_CAPACITY || capacity > MAX_ROOM_CAPACITY) throw Exception()
            val room = space.availableRoom(capacity)
            if (!room.addPerson(person)) return listOf(SendMessage().setText("Вы уже находитесь в комнате. Для выхода введите /leave"))
            sendBroadcastMessage(
                    SendMessage().setText("В комнату входит ${person.currentName}"), room.getOtherPersons(person)
            )
            room.log("JOIN user ${person.userToString()} as ${person.currentName}")
            saveState()
            return listOf(SendMessage().setText("""
                                                     Вы вошли в комнату ${room.hashCode()}.
                                                     Текущее количество участников: ${room.persons.size}
                                                     Максимальное количество участников: ${room.capacity}
                                                     Ваше имя в данной комнате: ${person.currentName}
                                                 """.trimIndent())
            )
        } catch (exc: Exception) {
            return listOf(SendMessage().setText("Неверный формат запроса. значение N должно быть от $MIN_ROOM_CAPACITY до $MAX_ROOM_CAPACITY включительно"))
        }
    }


}