package dev.brevitz.figurehunter.user.data

import dev.brevitz.figurehunter.core.storage.DataSource
import dev.brevitz.figurehunter.user.domain.User
import dev.brevitz.figurehunter.user.domain.UserRepository

class UserDataRepository(private val dataSource: DataSource<User>) : UserRepository {
    override fun get(id: String) = dataSource.get(id.toInt())

    override fun getAll() = dataSource.getAll()
}
