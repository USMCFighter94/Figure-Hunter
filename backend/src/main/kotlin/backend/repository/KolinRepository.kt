package backend.repository

object KolinRepository : Repository<String>() {
    private const val kolinResponse = """{
        "id": 1,
        "name": "Kolin Brevitz"
    }"""

    private val listOfResponses = listOf(kolinResponse)

    override fun add(newItem: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: String) = kolinResponse

    override fun getAll() = listOfResponses

    override fun remove(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}