package handlers.text

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class StartText : TextHandler {
    override fun handle(update: Update): List<SendMessage> {
        return listOf(SendMessage().setText("""
            Добро пожаловать в анонимные чат-комнаты

            Список доступных команд: /help
            
        """.trimIndent()))
    }
}