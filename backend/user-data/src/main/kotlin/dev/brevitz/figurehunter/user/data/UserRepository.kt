package dev.brevitz.figurehunter.user.data

import dev.brevitz.figurehunter.core.Repository
import dev.brevitz.figurehunter.user.domain.User

object UserRepository : Repository<User> {
    override fun add(newItem: User): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: String): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
