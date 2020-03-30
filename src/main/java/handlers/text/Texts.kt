package handlers.text

import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

enum class Texts(private val format: String, private val handler: TextHandler) {
    START("/start", StartText()),
    HELP("/help", HelpText()),
    JOIN("/join ", JoinText()),
    LEAVE("/leave", LeaveText()),
    STATS("/stats", StatsText()),

    ROOM("/room", RoomText()),
    CHAT_ROOM("/chatroom", ChatRoomText()),
    NO_ROOM("/noroom", NoRoomText()),
    DEFAULT("DEFAULT", DefaultText());


    companion object {
        fun handle(update: Update): List<SendMessage> {
            val message = update.message
            val command = values().find { message.text.toLowerCase().startsWith(it.format) }
            return command?.handler?.handle(update)
                    ?: if (space.person(message.from).hasChat)
                        CHAT_ROOM.handler.handle(update)
                    else NO_ROOM.handler.handle(update)
        }
    }

}