package dev.brevitz.figurehunter.core.storage

interface DataSource<T> {
    fun get(id: Int): T?
    fun getAll(): List<T>
}
