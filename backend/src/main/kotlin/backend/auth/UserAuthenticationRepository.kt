package backend.auth

import backend.LOG
import backend.auth.db.UserEntry
import backend.auth.db.Users
import backend.auth.login.UserCredentials
import backend.auth.register.RegistrationCredentials
import backend.auth.register.RegistrationResult
import backend.user.User
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class UserAuthenticationRepository : AuthenticationRepository {
    override fun find(id: Int): User? = transaction { UserEntry.findById(id) }?.toDomain()

    override fun find(user: UserCredentials): User? = transaction {
        UserEntry.find {
            Users.userName eq user.email and (Users.password eq user.password)
        }.firstOrNull()
    }?.toDomain()

    override fun create(user: RegistrationCredentials): RegistrationResult = try {
        val userEntry = transaction {
            UserEntry.new {
                firstName = user.firstName
                lastName = user.lastName
                userName = user.userName
                password = user.password
            }
        }

        RegistrationResult.Success(userEntry.toDomain())
    } catch (e: Exception) {
        LOG.error(e.message)
        RegistrationResult.Error(e.toString())
    }
}