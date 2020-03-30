package handlers.text

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class StartText : TextHandler {
    override fun handle(): List<SendMessage> {
        return listOf(SendMessage().setText("""
            Добро пожаловать в анонимные чат-комнаты

            Список доступных команд: /help
            
        """.trimIndent()))
    }
}