package handlers.photo

import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

enum class Photo(private val handler: PhotoHandler) {

    ALONE(AloneRoomPhoto()),
    NO_ROOM(NoRoomPhoto()),
    CHAT_ROOM(ChatRoomPhoto());

    companion object {
        fun handle(update: Update): List<SendMessage> {
            val person = space.person(update.message.from)
            return if (person.hasChat) {
                if (person.room.persons.size == 1) ALONE.handler.handle(update)
                else CHAT_ROOM.handler.handle(update)
            } else NO_ROOM.handler.handle(update)
        }
    }
}
