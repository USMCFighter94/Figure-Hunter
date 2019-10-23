package backend.figure.db

import backend.figure.Figure
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object Figures : IntIdTable() {
    val name = varchar("name", 255)
    val number = integer("number")
    val series = varchar("series", 255)
    val year = integer("year")
}

fun ResultRow.toDomain() = Figure(
    this[Figures.id].value,
    this[Figures.name],
    this[Figures.number],
    this[Figures.series],
    this[Figures.year]
)