package handlers.photo

import handlers.update
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

enum class Photo(private val handler: PhotoHandler) {

    ALONE(AloneRoomPhoto()),
    NO_ROOM(NoRoomPhoto()),
    CHAT_ROOM(ChatRoomPhoto());

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
