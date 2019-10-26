package backend.auth.db

import org.jetbrains.exposed.dao.IntIdTable

object Users : IntIdTable() {
    val firstName = varchar("first_name", 255)
    val lastName = varchar("last_name", 255)
    val userName = varchar("user_name", 255)
    val password = varchar("password", 255)
}