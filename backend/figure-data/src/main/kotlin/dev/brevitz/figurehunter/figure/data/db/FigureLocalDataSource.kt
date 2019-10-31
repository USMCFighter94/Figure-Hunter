package dev.brevitz.figurehunter.figure.data.db

import dev.brevitz.figurehunter.core.storage.LocalDataSource
import dev.brevitz.figurehunter.figure.domain.Figure
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class FigureLocalDataSource(private val localDatabase: Database) : LocalDataSource<Figure> {
    init {
        transaction(localDatabase) {
            SchemaUtils.create(Figures)
        }
    }

    override fun get(id: Int) = transaction(localDatabase) { FigureEntry.findById(id) }?.toDomain()

    override fun getAll() = transaction(localDatabase) { Figures.selectAll().map { it.toDomain() } }

    override fun save(item: Figure) {
        transaction(localDatabase) {
            Figures.insert {
                it[name] = item.name
                it[number] = item.num
                it[series] = item.series
                it[year] = item.year
            }
        }
    }
}
