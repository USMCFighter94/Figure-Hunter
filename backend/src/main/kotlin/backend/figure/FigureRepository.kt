package backend.figure

import backend.Repository
import backend.figure.db.FigureEntry
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

    override fun getAll() = transaction { FigureEntry.all() }.map { it.toDomain() }

    override fun remove(id: String) {
    }
}