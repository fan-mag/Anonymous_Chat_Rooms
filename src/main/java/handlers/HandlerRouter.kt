package handlers

import handlers.photo.Photo
import handlers.sticker.Sticker
import handlers.text.Texts
import model.Person
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

object HandlerRouter {

    fun handleUpdate(): List<SendMessage> {
        space.addPerson(Person(update.message.from))
        val replies = ArrayList<SendMessage>()

        if (update.message.hasText())
            replies.addAll(Texts.handle())

        if (update.message.hasPhoto())
            replies.addAll(Photo.handle())

        if (update.message.hasSticker())
            replies.addAll(Sticker.handle())

        return replies
    }

    fun routeTo(stub: Texts.System): List<SendMessage> {
        return stub.handler.handle()
    }
}