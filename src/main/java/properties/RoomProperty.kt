package properties

object RoomProperty {
    val MAX_ROOM_CAPACITY = Property.getProperty("MAX_ROOM_CAPACITY").toString().toInt()
    val MIN_ROOM_CAPACITY = Property.getProperty("MIN_ROOM_CAPACITY").toString().toInt()

}