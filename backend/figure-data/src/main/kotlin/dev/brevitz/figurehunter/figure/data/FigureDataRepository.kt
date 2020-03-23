package dev.brevitz.figurehunter.figure.data

import dev.brevitz.figurehunter.core.storage.DataSource
import dev.brevitz.figurehunter.core.storage.LocalDataSource
import dev.brevitz.figurehunter.figure.domain.Figure
import dev.brevitz.figurehunter.figure.domain.FigureRepository

class FigureDataRepository(
    private val localDataSource: LocalDataSource<Figure>,
    private val dataSource: DataSource<Figure>
) : FigureRepository {
    override fun get(id: String) = localDataSource.get(id.toInt())
        ?: dataSource.get(id.toInt())
            ?.also { localDataSource.save(it) }

    override fun getAll() = dataSource.getAll()
}
