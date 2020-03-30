package handlers

import handlers.text.Texts
import model.Person
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

object HandlerRouter {

    fun handleUpdate(update: Update): List<SendMessage> {
        space.addPerson(Person(update.message.from))


        if (update.message.hasText()) return Texts.handle(update)
        if (update.message.hasAnimation()) return emptyList()
        if (update.message.hasAudio()) return emptyList()
        return listOf(SendMessage().setText("Не знаю, как с этим работать"))
    }
}