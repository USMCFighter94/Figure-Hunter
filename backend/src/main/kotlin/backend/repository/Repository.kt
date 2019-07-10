package backend.repository

abstract class Repository<T> {
    abstract fun add(newItem: T): T
    abstract fun get(id: String): T?
    abstract fun getAll(): List<T>
    abstract fun remove(id: String)
}