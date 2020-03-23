package dev.brevitz.figurehunter.user.domain

interface UserRepository {
    fun get(id: String): User?
    fun getAll(): List<User>
}
