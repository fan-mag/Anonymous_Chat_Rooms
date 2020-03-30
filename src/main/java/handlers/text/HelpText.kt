package handlers.text

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import properties.RoomProperty.MAX_ROOM_CAPACITY
import properties.RoomProperty.MIN_ROOM_CAPACITY

class HelpText : TextHandler {

    private enum class Commands(val prefix: String, val description: String) {
        HELP("/help", "Вывод доступных команд для использования"),
        JOIN("/join N", "Войти в комнату (макс. участников от $MIN_ROOM_CAPACITY до $MAX_ROOM_CAPACITY)"),
        LEAVE("/leave", "Выйти из текущей комнаты"),
        STATS("/stats", "Статистика по использованию");
    }

    override fun handle(): List<SendMessage> {
        val sb = StringBuilder()
        Commands.values().forEach { sb.append(it.prefix).append(" - ").append(it.description).append(System.lineSeparator()) }
        return listOf(SendMessage().setText(sb.toString()))
    }
}