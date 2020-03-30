package handlers.text

import handlers.text.Texts.System.CHAT_ROOM
import handlers.text.Texts.System.NO_ROOM
import handlers.text.general.EmptyText
import handlers.text.general.HelpText
import handlers.text.general.NoRoomText
import handlers.text.general.StartText
import handlers.text.join.InRoomJoinText
import handlers.text.join.IncorrectJoinText
import handlers.text.join.JoinText
import handlers.text.leave.LeaveText
import handlers.text.leave.OutRoomLeaveText
import handlers.text.roominfo.RoomInfoText
import handlers.update
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import properties.RoomProperty

class Texts {

    enum class Commands(val format: String, val description: String, val handler: TextHandler) {
        START("/start", "выводит приветствие бота", StartText()),
        HELP("/help", "Вывод доступных команд для использования", HelpText()),
        JOIN("/join ", "Войти в комнату (макс. участников от ${RoomProperty.MIN_ROOM_CAPACITY} до ${RoomProperty.MAX_ROOM_CAPACITY}), использование - /join <N>", JoinText()),
        LEAVE("/leave", "Выйти из текущей комнаты", LeaveText()),
        STATS("/stats", "Статистика по использованию бота", StatsText()),
        ROOM("/roominfo", "Вывод информации о текущей комнате", RoomInfoText()),
    }

    enum class System(val handler: TextHandler) {
        INCORRECT_JOIN(IncorrectJoinText()),
        IN_ROOM_JOIN(InRoomJoinText()),
        EMPTY(EmptyText()),
        OUT_ROOM_LEAVE(OutRoomLeaveText()),
        CHAT_ROOM(ChatRoomText()),
        NO_ROOM(NoRoomText());
    }

    companion object {
        fun handle(): List<SendMessage> {
            val message = update.message
            val command = Commands.values().find { message.text.toLowerCase().startsWith(it.format) }
            return command?.handler?.handle()
                    ?: if (space.person(message.from).hasChat)
                        CHAT_ROOM.handler.handle()
                    else NO_ROOM.handler.handle()
        }
    }

}