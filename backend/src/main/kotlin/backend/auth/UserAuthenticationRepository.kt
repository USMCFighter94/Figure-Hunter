package backend.auth

import backend.LOG
import backend.auth.db.UserEntry
import backend.auth.db.Users
import backend.auth.login.UserCredentials
import backend.auth.register.RegistrationCredentials
import backend.auth.register.RegistrationResult
import backend.user.User
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt

class UserAuthenticationRepository : AuthenticationRepository {
    override fun find(id: Int): User? = transaction { UserEntry.findById(id) }?.toDomain()

    override fun find(user: UserCredentials): User? {
        val userEntry = transaction {
            UserEntry.find { Users.userName eq user.email }.firstOrNull()
        }

        if (userEntry != null) {
            if (!BCrypt.checkpw(user.password, userEntry.password)) {
                return null
            }
        }

        return userEntry?.toDomain()
    }

    override fun create(user: RegistrationCredentials): RegistrationResult = try {
        val userEntry = transaction {
            UserEntry.new {
                firstName = user.firstName
                lastName = user.lastName
                userName = user.userName
                password = BCrypt.hashpw(user.password, BCrypt.gensalt())
            }
        }

        RegistrationResult.Success(userEntry.toDomain())
    } catch (e: Exception) {
        LOG.error(e.message)
        RegistrationResult.Error(e.toString())
    }
}