package dev.brevitz.figurehunter.figure.data

import dev.brevitz.figurehunter.core.Repository
import dev.brevitz.figurehunter.core.storage.DataSource
import dev.brevitz.figurehunter.core.storage.LocalDataSource
import dev.brevitz.figurehunter.figure.domain.Figure

class FigureRepository(
    private val localDataSource: LocalDataSource<Figure>,
    private val dataSource: DataSource<Figure>
) : Repository<Figure> {
    override fun add(newItem: Figure): Figure {
        return newItem
    }

    override fun get(id: String) = localDataSource.get(id.toInt())
        ?: dataSource.get(id.toInt())!!
            .also { localDataSource.save(it) }

    override fun getAll() = dataSource.getAll()

    override fun remove(id: String) {
    }
}
