package model

import org.json.JSONArray
import org.json.JSONObject
import org.telegram.telegrambots.meta.api.objects.User
import java.io.Serializable

val space by lazy { Space() }

class Space : Jsonnable, Serializable {
    val rooms = HashSet<Room>()
    val persons = HashSet<Person>()

    fun addPerson(person: Person) {
        persons.add(person)
    }

    fun person(user: User): Person {
        return persons.first { it.user == user }
    }

    fun availableRoom(capacity: Int): Room {
        val room = rooms.find { it.capacity == capacity && it.available }
        if (room != null) return room
        val newRoom = Room(capacity)
        newRoom.log("CREATED room")
        return newRoom
    }

    fun getPersonRoom(person: Person): Room {
        return rooms.find { it.persons.contains(person) }!!
    }

    override fun toJson(): String {
        return JSONObject().put("rooms", JSONArray(rooms.map {it.toJson()})).put("persons", JSONArray(persons.map {it.toJson()})).toString()
    }
}