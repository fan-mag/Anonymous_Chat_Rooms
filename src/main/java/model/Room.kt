package model

import executors.StateSaver.saveState
import org.json.JSONArray
import org.json.JSONObject
import properties.StateLocation.ROOMS_LOG
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class Room(val capacity: Int) : Jsonnable {


    val persons = ArrayList<Person>()
    var hashcode: Int = 0

    val available: Boolean
        get() = persons.size < capacity

    init {
        space.rooms.add(this)
        hashcode = Random().nextInt()
    }


    fun addPerson(person: Person): Boolean {
        if (!available) return false
        if (person.hasChat) return false
        persons.add(person)
        person.hasChat = true
        if (person.currentName == "Безымянный")
            person.currentName = getAvailableCurrentName()
        saveState()
        log("JOIN user ${person.userToString()} as ${person.currentName}")
        return true
    }

    fun removePerson(person: Person) {
        persons.remove(person)
        person.hasChat = false
        person.currentName = "Безымянный"
        log("LEFT user ${person.userToString()} as ${person.currentName}")
        saveState()
        if (isEmpty()) {
            log("CLOSE room")
            space.rooms.remove(this)
            saveState()
        }
    }

    fun getOtherPersons(except: Person): List<Person> {
        return persons.filterNot { it == except }
    }

    private fun getAvailableCurrentName(): String {
        val takenNames = persons.map { it.currentName }
        val availableNames = ChatNames.values().map { it.name }
        return availableNames.minus(takenNames).random()
    }

    fun log(textToLog: String) {
        File("${ROOMS_LOG}Room-${this.hashCode()}.txt").appendText("${Date()} - $textToLog\n")
        println("${Date()} - ${this.hashCode()} - $textToLog")
    }

    private fun isEmpty(): Boolean {
        return persons.isEmpty()
    }

    override fun toJson(): String {
        return JSONObject().put("capacity", capacity).put("persons", JSONArray(persons.map { it.toJson() })).put("hashCode", hashCode()).toString()
    }

    override fun hashCode(): Int {
        return hashcode
    }
}