package backend.user

import java.security.Principal

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val userName: String,
    val collection: FigureCollection
) : Principal {
    override fun getName(): String = userName
}