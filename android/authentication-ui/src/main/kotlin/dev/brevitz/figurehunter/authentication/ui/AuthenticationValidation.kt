package dev.brevitz.figurehunter.authentication.ui

import java.util.regex.Pattern

private val PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=*])(?=\\S+$).{4,}$")

fun validEmail(email: String?) = !email.isNullOrBlank()

/**
 * Valid passwords contain 8 or more characters, a number,
 * and at least one each of a lowercase, uppercase, and special character
 */
fun validPassword(password: String?) = !password.isNullOrBlank() &&
        password.length >= 8 &&
        PASSWORD_PATTERN.matcher(password).matches()
