package dev.brevitz.figurehunter.user.data

import dev.brevitz.figurehunter.user.domain.User
import io.kotlintest.properties.Gen

class UserGenerator : Gen<User> {
    override fun constants() = emptyList<User>()

    override fun random() = generateSequence {
        User(
            Gen.int().random().first(),
            Gen.string().random().first(),
            Gen.string().random().first(),
            Gen.string().random().first()
        )
    }
}
