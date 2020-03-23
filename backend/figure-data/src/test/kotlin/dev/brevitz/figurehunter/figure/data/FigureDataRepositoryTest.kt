package dev.brevitz.figurehunter.figure.data

import dev.brevitz.figurehunter.figure.data.db.FigureDataSource
import dev.brevitz.figurehunter.figure.data.db.FigureLocalDataSource
import dev.brevitz.figurehunter.figure.domain.Figure
import io.kotlintest.properties.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import io.mockk.every
import io.mockk.mockk

class FigureDataRepositoryTest : FunSpec() {
    private val mockLocalDataSource = mockk<FigureLocalDataSource>()
    private val mockDataSource = mockk<FigureDataSource>()
    private val figureGenerator = FigureGenerator(

    )
    private val repository = FigureDataRepository(mockLocalDataSource, mockDataSource)

    init {
        context("Empty Local Data Source Should Read from Main Data Source") {
            every { mockLocalDataSource.get(any()) } returns null
            every { mockLocalDataSource.save(any()) } returns Unit

            context("Get") {
                test("Invalid Id") {
                    every { mockDataSource.get(-1) } returns null

                    repository.get("-1") shouldBe null
                }

                test("Id exists in data source") {
                    forAll(figureGenerator) { figure: Figure ->
                        every { mockDataSource.get(figure.id) } returns figure

                        repository.get(figure.id.toString()) == figure
                    }
                }
            }
        }

        context("Local Data Source Is Not Empty") {
            every { mockLocalDataSource.save(any()) } returns Unit

            context("Get") {
                test("Invalid Id") {
                    every { mockLocalDataSource.get(-1) } returns null
                    every { mockDataSource.get(-1) } returns null

                    repository.get("-1") shouldBe null
                }

                test("Id exists in data source") {
                    forAll(figureGenerator) { figure: Figure ->
                        every { mockLocalDataSource.get(figure.id) } returns figure

                        repository.get(figure.id.toString()) == figure
                    }
                }
            }
        }
    }
}
