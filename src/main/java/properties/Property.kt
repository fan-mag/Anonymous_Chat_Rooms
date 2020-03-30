package properties

import java.io.FileInputStream
import java.util.*

object Property {
    private const val PROPERTIES_PATH = "src\\main\\resources\\local.properties"
    private val properties: Properties = Properties()

    fun getProperty(key: String): Any? {
        properties.load(FileInputStream(PROPERTIES_PATH))
        return properties[key]
    }
}