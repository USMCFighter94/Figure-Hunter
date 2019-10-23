package backend.figure.db

import org.jetbrains.exposed.dao.IntIdTable

object Figures : IntIdTable() {
    val name = varchar("name", 255)
    val number = integer("number")
    val series = varchar("series", 255)
    val year = integer("year")
}