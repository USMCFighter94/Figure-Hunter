package backend

interface Repository<T> {
    fun add(newItem: T): T
    fun get(id: String): T
    fun getAll(): List<T>
    fun remove(id: String)
}