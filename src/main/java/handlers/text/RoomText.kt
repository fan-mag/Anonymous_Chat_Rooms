package handlers.text

import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class RoomText : TextHandler {

    override fun handle(update: Update): List<SendMessage> {
        val person = space.person(update.message.from)
        val sb1 = StringBuilder("""В комнате ${person.room.hashCode()} в данный момент находятся:
Вы: ${person.currentName}${System.lineSeparator()}
        """.trimMargin())
        person.room.persons.filterNot { it == person }.forEach { sb1.append("${it.currentName}${System.lineSeparator()}") }
        return listOf(
                SendMessage().setText(sb1.toString())
        )
    }
}