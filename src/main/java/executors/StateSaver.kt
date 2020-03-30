package executors

import model.Person
import model.Room
import model.space
import org.json.JSONException
import org.json.JSONObject
import org.telegram.telegrambots.meta.api.objects.User
import properties.StateLocation
import java.io.File
import java.util.*

object StateSaver {
    fun saveState() {
        val spaceJson = JSONObject(space.toJson()).toString()
        File(StateLocation.PATH).writeText(spaceJson)
    }

    fun loadState() {
        if (File(StateLocation.PATH).exists()) {
            val spaceJson = JSONObject(File(StateLocation.PATH).readText())
            val personArray = spaceJson.getJSONArray("persons")
            val persons = HashSet<Person>()
            personArray.forEach {
                val personJson = JSONObject(it as String)
                val userJson = personJson.getJSONObject("user")
                val tgUser = User(
                        get(userJson, "id") as Int?,
                        get(userJson, "firstName") as String?,
                        get(userJson, "bot") as Boolean?,
                        get(userJson, "lastName") as String?,
                        get(userJson, "userName") as String?,
                        get(userJson, "languageCode") as String?)
                val person = Person(tgUser)
                person.hasChat = false
                person.currentName = personJson.getString("currentName")
                persons.add(person)
            }
            persons.forEach { space.persons.add(it) }
            val roomsArray = spaceJson.getJSONArray("rooms")
            roomsArray.forEach {
                val roomJson = JSONObject(it as String)
                val room = Room(roomJson.getInt("capacity"))
                room.hashcode = roomJson.getInt("hashCode")
                roomJson.getJSONArray("persons").forEach {
                    val personJson = JSONObject(it as String)
                    val person = space.persons.first { it.user.id == personJson.getJSONObject("user").getInt("id") }
                    room.addPerson(person)
                }
            }
        }
    }

    private fun get(json: JSONObject, key: String): Any? {
        return try {
            json.get(key)
        } catch (exception: JSONException) {
            null
        }
    }
}