package handlers

import handlers.photo.Photo
import handlers.text.Texts
import model.Person
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

object HandlerRouter {

    fun handleTextUpdate(): List<SendMessage> {
        space.addPerson(Person(update.message.from))
        return Texts.handle()
    }

    fun handlePhotoUpdate(): List<SendMessage> {
        space.addPerson(Person(update.message.from))
        return Photo.handle()
    }
}