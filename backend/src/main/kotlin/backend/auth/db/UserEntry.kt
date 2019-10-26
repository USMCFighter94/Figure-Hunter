package backend.auth.db

import backend.user.FigureCollection
import backend.user.User
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class UserEntry(id: EntityID<Int>) : IntEntity(id) {
    var firstName by Users.firstName
    var lastName by Users.lastName
    var userName by Users.userName
    var password by Users.password

    fun toDomain() = User(id.value, firstName, lastName, userName, FigureCollection(emptyList()))

    companion object : IntEntityClass<UserEntry>(Users)
}