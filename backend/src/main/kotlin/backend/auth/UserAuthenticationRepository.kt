package backend.auth

import backend.auth.login.UserCredentials
import backend.auth.register.RegistrationCredentials
import backend.auth.register.RegistrationResult
import backend.user.User

class UserAuthenticationRepository : AuthenticationRepository {
    override fun find(id: Int): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun find(user: UserCredentials): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(user: RegistrationCredentials): RegistrationResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}