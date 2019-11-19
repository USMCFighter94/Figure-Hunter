package dev.brevitz.figurehunter.user.data

import dev.brevitz.figurehunter.core.Repository
import dev.brevitz.figurehunter.core.storage.DataSource
import dev.brevitz.figurehunter.user.domain.User

class UserRepository(private val dataSource: DataSource<User>) : Repository<User> {
    override fun add(newItem: User): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: String) = dataSource.get(id.toInt())

    override fun getAll() = dataSource.getAll()

    override fun remove(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
