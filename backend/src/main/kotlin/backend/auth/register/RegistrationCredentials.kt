package backend.auth.register

data class RegistrationCredentials(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String
)