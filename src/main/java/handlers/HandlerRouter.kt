package handlers

import handlers.photo.Photo
import handlers.text.Texts
import model.Person
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

object HandlerRouter {

    fun handleTextUpdate(update: Update): List<SendMessage> {
        space.addPerson(Person(update.message.from))
        return Texts.handle(update)
    }

    fun handlePhotoUpdate(update: Update): List<SendMessage> {
        space.addPerson(Person(update.message.from))
        return Photo.handle(update)
    }
}