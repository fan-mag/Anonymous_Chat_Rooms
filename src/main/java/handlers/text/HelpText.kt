package handlers.text

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import properties.RoomProperty.MAX_ROOM_CAPACITY
import properties.RoomProperty.MIN_ROOM_CAPACITY
import java.util.*

class HelpText : TextHandler {

    private enum class Commands(val prefix: String, val description: String) {
        HELP("/help", "Вывод доступных команд для использования"),
        JOIN("/join N", "Войти в комнату (макс. участников от $MIN_ROOM_CAPACITY до $MAX_ROOM_CAPACITY)"),
        LEAVE("/leave", "Выйти из текущей комнаты"),
        STATS("/stats", "Статистика по использованию");
    }

    override fun handle(update: Update): List<SendMessage> {
        val sb = StringBuilder()
        Commands.values().forEach { sb.append(it.prefix).append(" - ").append(it.description).append(System.lineSeparator()) }
        val helpMessage = SendMessage().setText(sb.toString())
        return listOf(helpMessage)
    }

    private fun <T> listOf(t: T): List<T> {
        val list: MutableList<T> = ArrayList()
        list.add(t)
        return list
    }
}