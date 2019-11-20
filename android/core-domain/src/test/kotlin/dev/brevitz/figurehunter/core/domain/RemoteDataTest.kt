package dev.brevitz.figurehunter.core.domain

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import io.reactivex.Maybe

class RemoteDataTest : FunSpec() {
    init {
        context("toMaybeError") {
            test("RemoteData.NotAsked") {
                RemoteData.NotAsked.toMaybeError() shouldBe Maybe.empty()
            }

            test("RemoteData.Loading") {
                RemoteData.Loading.toMaybeError() shouldBe Maybe.empty()
            }

            test("RemoteData.Success") {
                RemoteData.Success("").toMaybeError() shouldBe Maybe.empty()
            }

            test("RemoteData.Error") {
                RemoteData.Error("There was an error").toMaybeError()
                    .test()
                    .assertValue("There was an error")
            }
        }

        context("mapSuccess") {
            var remoteData: RemoteData<TestData, String>

            test("RemoteData.NotAsked") {
                remoteData = RemoteData.NotAsked
                remoteData.mapSuccess { it.data } shouldBe RemoteData.NotAsked
            }

            test("RemoteData.Loading") {
                remoteData = RemoteData.Loading
                remoteData.mapSuccess { it.data } shouldBe RemoteData.Loading
            }

            test("RemoteData.Success") {
                remoteData = RemoteData.succeed(TestData("Hello!"))
                remoteData.mapSuccess { it.data } shouldBe RemoteData.succeed("Hello!")
            }

            test("RemoteData.Error") {
                remoteData = RemoteData.error("There was an error")
                remoteData.mapSuccess { it.data } shouldBe RemoteData.error("There was an error")
            }
        }
    }

    private data class TestData(val data: String)
}
