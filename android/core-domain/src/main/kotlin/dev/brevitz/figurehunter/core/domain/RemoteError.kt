package dev.brevitz.figurehunter.core.domain

sealed class RemoteError {
    object SyncError : RemoteError() {
        override fun getUserError() = "Sync Error"
    }

    data class HttpError(val code: Int, val message: String) : RemoteError() {
        override fun getUserError() = message
    }

    data class ParsingError(val error: Throwable) : RemoteError() {
        override fun getUserError(): String = error.localizedMessage
    }

    abstract fun getUserError(): String
}
