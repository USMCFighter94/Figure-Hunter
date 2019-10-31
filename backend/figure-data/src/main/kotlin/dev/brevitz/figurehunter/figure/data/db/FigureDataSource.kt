package dev.brevitz.figurehunter.figure.data.db

import dev.brevitz.figurehunter.core.storage.DataSource
import dev.brevitz.figurehunter.figure.domain.Figure
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class FigureDataSource(private val database: Database) : DataSource<Figure> {
    override fun get(id: Int) = transaction(database) { FigureEntry.findById(id) }?.toDomain()

    override fun getAll() = transaction(database) { Figures.selectAll().map { it.toDomain() } }
}
