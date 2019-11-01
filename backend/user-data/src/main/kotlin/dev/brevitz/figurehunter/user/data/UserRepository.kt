package dev.brevitz.figurehunter.user.data

import dev.brevitz.figurehunter.core.Repository
import dev.brevitz.figurehunter.user.data.db.UserEntry
import dev.brevitz.figurehunter.user.data.db.Users
import dev.brevitz.figurehunter.user.data.db.toDomain
import dev.brevitz.figurehunter.user.domain.User
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object UserRepository : Repository<User> {
    override fun add(newItem: User): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: String): User? = transaction { UserEntry.findById(id.toInt()) }?.toDomain()

    override fun getAll(): List<User> = transaction { Users.selectAll() }.mapNotNull { it.toDomain() }

    override fun remove(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
