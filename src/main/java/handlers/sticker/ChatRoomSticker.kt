package handlers.sticker

import executors.AnonymousRoomsRunner.Companion.broadcast
import handlers.photo.StickerHandler
import handlers.update
import model.space
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendSticker

class ChatRoomSticker: StickerHandler {
    override fun handle(): List<SendMessage> {
        val person = space.person(update.message.from)
        val sticker = update.message.sticker.fileId
        person.room.log("${person.currentName}: STICKER $sticker")
        broadcast(
                message = SendMessage().setText("${person.currentName} прислал(а) стикер"),
                persons = person.room.getOtherPersons(person)
        )
        broadcast(
                sticker = SendSticker().setSticker(sticker),
                persons = person.room.getOtherPersons(person)
        )
        return emptyList()
    }
}