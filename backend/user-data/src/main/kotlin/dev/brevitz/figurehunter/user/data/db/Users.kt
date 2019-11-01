package dev.brevitz.figurehunter.user.data.db

import dev.brevitz.figurehunter.user.domain.User
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object Users : IntIdTable() {
    val firstName = varchar("first_name", 255)
    val lastName = varchar("last_name", 255)
    val userName = varchar("user_name", 255)
    val password = varchar("password", 255)
}

fun ResultRow.toDomain() = User(
    this[Users.id].value,
    this[Users.firstName],
    this[Users.lastName],
    this[Users.userName]
)
