package dev.brevitz.figurehunter.figure.data

import dev.brevitz.figurehunter.core.Repository
import dev.brevitz.figurehunter.figure.data.db.FigureEntry
import dev.brevitz.figurehunter.figure.data.db.Figures
import dev.brevitz.figurehunter.figure.data.db.toDomain
import dev.brevitz.figurehunter.figure.domain.Figure
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object FigureRepository : Repository<Figure> {
    override fun add(newItem: Figure): Figure {
        return newItem
    }

    override fun get(id: String) = transaction { FigureEntry.findById(id.toInt()) }
        ?.toDomain()
        ?: Figure(
            0,
            "",
            0,
            "",
            1969
        )

    override fun getAll() = transaction { Figures.selectAll().map { it.toDomain() } }

    override fun remove(id: String) {
    }
}
