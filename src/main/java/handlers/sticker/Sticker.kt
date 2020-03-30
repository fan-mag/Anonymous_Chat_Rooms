package handlers.sticker

import handlers.Handler
import handlers.text.chat.AloneRoomText
import handlers.text.general.NoRoomText
import handlers.update
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

enum class Sticker(private val handler: Handler) {

    ALONE(AloneRoomText()),
    NO_ROOM(NoRoomText()),
    CHAT_ROOM(ChatRoomSticker());

    companion object {
        fun handle(): List<SendMessage> {
            val person = space.person(update.message.from)
            return if (person.hasChat) {
                if (person.room.persons.size == 1) ALONE.handler.handle()
                else CHAT_ROOM.handler.handle()
            } else NO_ROOM.handler.handle()
        }
    }
}
