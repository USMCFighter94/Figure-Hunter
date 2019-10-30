package dev.brevitz.figurehunter.figure.data.db

import dev.brevitz.figurehunter.figure.domain.Figure
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class FigureEntry(id: EntityID<Int>) : IntEntity(id) {
    private val name by Figures.name
    private val number by Figures.number
    private val series by Figures.series
    private val year by Figures.year

    fun toDomain() = Figure(id.value, name, number, series, year)

    companion object : IntEntityClass<FigureEntry>(Figures)
}
