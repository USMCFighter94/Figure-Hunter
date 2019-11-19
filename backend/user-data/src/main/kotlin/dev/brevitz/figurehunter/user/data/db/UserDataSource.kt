package dev.brevitz.figurehunter.user.data.db

import dev.brevitz.figurehunter.core.storage.DataSource
import dev.brevitz.figurehunter.user.domain.User
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserDataSource(private val database: Database) : DataSource<User> {
    override fun get(id: Int) = transaction(database) { UserEntry.findById(id) }?.toDomain()

    override fun getAll() = transaction(database) { Users.selectAll() }.mapNotNull { it.toDomain() }
}
