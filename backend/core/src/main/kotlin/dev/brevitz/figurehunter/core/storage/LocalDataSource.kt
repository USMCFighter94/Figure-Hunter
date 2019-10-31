package dev.brevitz.figurehunter.core.storage

interface LocalDataSource<T> {
    fun get(id: Int): T?
    fun getAll(): List<T>
    fun save(item: T)
}
