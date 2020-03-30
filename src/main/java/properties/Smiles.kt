package properties

enum class Smiles(private val code: String) {
    FUCK_BLACK("\uD83D\uDD95\uD83C\uDFFF");

    override fun toString(): String {
        return code
    }

}