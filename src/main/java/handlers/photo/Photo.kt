package handlers.photo

import handlers.Handler
import handlers.HandlerRouter.routeTo
import handlers.text.Texts.System.ALONE_ROOM
import handlers.text.Texts.System.NO_ROOM
import handlers.update
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

enum class Photo(private val handler: Handler) {

    CHAT_ROOM(ChatRoomPhoto());

    companion object {
        fun handle(): List<SendMessage> {
            val person = space.person(update.message.from)
            return if (person.hasChat) {
                if (person.room.persons.size == 1) routeTo(ALONE_ROOM)
                else CHAT_ROOM.handler.handle()
            } else routeTo(NO_ROOM)
        }
    }
}
