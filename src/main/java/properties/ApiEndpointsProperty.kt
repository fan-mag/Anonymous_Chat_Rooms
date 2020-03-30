package properties

import properties.Property.getProperty

object ApiEndpointsProperty {
        val BOT_TOKEN = getProperty("BOT_TOKEN")

        val API_URL = getProperty("API_URL")
        val API_PROTOCOL = getProperty("API_PROTOCOL")
}