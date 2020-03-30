package handlers.photo

import executors.AnonymousRoomsRunner
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.Update

class ChatRoomPhoto : PhotoHandler {
    override fun handle(update: Update): List<SendMessage> {
        val person = space.person(update.message.from)
        val photoId = update.message.photo.last().fileId
        var caption = update.message.caption
        if (caption.isNullOrEmpty()) caption = ""
        person.room.log("${person.currentName}: PHOTO $photoId")
        AnonymousRoomsRunner.sendBroadcastPhoto(
                photo = SendPhoto().setPhoto(photoId).setCaption("${person.currentName}: $caption"),
                persons = person.room.getOtherPersons(person)
        )
        return emptyList()
    }
}