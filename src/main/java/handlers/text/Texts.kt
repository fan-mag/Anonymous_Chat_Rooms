package handlers.text

import handlers.text.join.InRoomJoinText
import handlers.text.join.IncorrectJoinText
import handlers.text.join.JoinText
import handlers.text.leave.LeaveText
import handlers.text.leave.OutRoomLeaveText
import handlers.update
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import properties.Property.STUB_TEXT

enum class Texts(private val format: String, val handler: TextHandler) {
    START("/start", StartText()),
    HELP("/help", HelpText()),
    JOIN("/join ", JoinText()),
    INCORRECT_JOIN(STUB_TEXT, IncorrectJoinText()),
    IN_ROOM_JOIN(STUB_TEXT, InRoomJoinText()),

    LEAVE("/leave", LeaveText()),
    OUT_ROOM_LEAVE(STUB_TEXT, OutRoomLeaveText()),
    STATS("/stats", StatsText()),

    ROOM("/room", RoomText()),
    CHAT_ROOM("/chatroom", ChatRoomText()),
    NO_ROOM(STUB_TEXT, NoRoomText());


    companion object {
        fun handle(): List<SendMessage> {
            val message = update.message
            val command = values().find { message.text.toLowerCase().startsWith(it.format) }
            return command?.handler?.handle()
                    ?: if (space.person(message.from).hasChat)
                        CHAT_ROOM.handler.handle()
                    else NO_ROOM.handler.handle()
        }
    }

}