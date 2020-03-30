package properties

import properties.Property.getProperty

object StateLocation {
    val PATH = getProperty("STATE_PATH").toString()

    val ROOMS_LOG = getProperty("ROOMS_LOG").toString()
}