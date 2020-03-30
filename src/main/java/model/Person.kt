package model

import org.json.JSONObject
import org.telegram.telegrambots.meta.api.objects.User

class Person(val user: User) : Jsonnable {
    var hasChat: Boolean = false
    var currentName: String = "Безымянный"


    val room: Room
        get() = space.getPersonRoom(this)




    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Person
        if (user != other.user) return false
        return true
    }

    override fun hashCode(): Int {
        return user.hashCode()
    }

    override fun toJson(): String {
        return JSONObject().put("user", JSONObject(user)).put("hasChat", hasChat).put("currentName", currentName).toString()
    }

    fun userToString(): String {
        return "[id:${user.id},userName:${user.userName},firstName:${user.firstName},lastName:${user.lastName}]"
    }
}
