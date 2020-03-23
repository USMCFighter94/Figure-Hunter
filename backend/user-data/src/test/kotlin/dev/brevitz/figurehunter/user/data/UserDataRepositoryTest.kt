package dev.brevitz.figurehunter.user.data

import dev.brevitz.figurehunter.user.data.db.UserDataSource
import dev.brevitz.figurehunter.user.domain.User
import io.kotlintest.properties.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import io.mockk.every
import io.mockk.mockk

class UserDataRepositoryTest : FunSpec() {
    private val mockDataSource = mockk<UserDataSource>()
    private val userGenerator = UserGenerator()
    private val repository = UserDataRepository(mockDataSource)

    init {
        context("Main Data Source") {
            context("Get") {
                test("Negative Id") {
                    every { mockDataSource.get(-1) } returns null
                    repository.get("-1") shouldBe null
                }

                test("Invalid Id") {
                    every { mockDataSource.get(120) } returns null
                    repository.get("120") shouldBe null
                }

                test("Id exists in data source") {
                    forAll(userGenerator) { user: User ->
                        every { mockDataSource.get(user.id) } returns user
                        repository.get(user.id.toString()) == user
                    }
                }
            }

            context("Get all") {
                test("EmptyList") {
                    every { mockDataSource.getAll() } returns emptyList()
                    repository.getAll() shouldBe emptyList()
                }

                test("Users exist in data source") {
                    val users = userGenerator.random().take(10).toList()
                    every { mockDataSource.getAll() } returns users
                    repository.getAll() == users
                }
            }
        }
    }
}
