package handlers.text.general

import handlers.text.TextHandler
import handlers.text.Texts
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class HelpText : TextHandler {
    override fun handle(): List<SendMessage> {
        val sb = StringBuilder()
        Texts.Commands.values().forEach { sb.append(it.format).append(" - ").append(it.description).append(System.lineSeparator()) }
        return listOf(SendMessage().setText(sb.toString()))
    }
}