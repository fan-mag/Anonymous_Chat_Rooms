package handlers.text

import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import properties.RoomProperty.MAX_ROOM_CAPACITY
import properties.RoomProperty.MIN_ROOM_CAPACITY

class StatsText : TextHandler {
    override fun handle(update: Update): List<SendMessage> {
        val sb1 = StringBuilder("Статистика по чатам: \n [N] | Комнат | Участников\n")
        for (i in MIN_ROOM_CAPACITY until MAX_ROOM_CAPACITY + 1) {
            val totalRooms = space.rooms.filter { it.capacity == i }.size
            var totalPersons = 0
            space.rooms.filter { it.capacity == i }.forEach { totalPersons += it.persons.size }
            sb1.append(" [$i] | $totalRooms | $totalPersons\n")
        }
        return listOf(
            SendMessage().setText(sb1.toString())
        )
    }
}