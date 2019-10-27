package dev.brevitz.figurehunter.authentication.ui

import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec

class AuthenticationValidationTest : DescribeSpec() {
    init {
        describe("Email validation") {
            context("Valid emails") {
                it("1 character string") {
                    validEmail("a") shouldBe true
                }

                it("Normal email") {
                    validEmail("colin@brevitz.com") shouldBe true
                }

                it(".net email") {
                    validEmail("colin@brevitz.net") shouldBe true
                }

                it("Uk email") {
                    validEmail("colin@brevitz.co.uk") shouldBe true
                }

                it("One letter email") {
                    validEmail("c@b.com") shouldBe true
                }
            }

            context("Invalid emails") {
                it("null email") {
                    validEmail(null) shouldBe false
                }

                it("Blank email") {
                    validEmail("") shouldBe false
                }
            }
        }

        describe("Password validation") {
            context("Valid passwords") {
                it("Random string 1") {
                    validPassword("asDfg4!hjkl") shouldBe true
                }

                it("Random string 2") {
                    validPassword("As3jgld$") shouldBe true
                }
            }

            context("Invalid passwords") {
                it("Null password") {
                    validPassword(null) shouldBe false
                }

                it("Blank password") {
                    validPassword("") shouldBe false
                }

                it("1 letter character") {
                    validPassword("a") shouldBe false
                }

                it("All characters - sub 8 length") {
                    validPassword("asdfghj") shouldBe false
                }

                it("All characters - 8 length") {
                    validPassword("asdfghjkl") shouldBe false
                }

                it("All characters - 8 length w/ capital") {
                    validPassword("asdFghfjl") shouldBe false
                }

                it("8 length with number") {
                    validPassword("asdfghjk1") shouldBe false
                }

                it("No special character") {
                    validPassword("asdFghjk1") shouldBe false
                }
            }
        }
    }
}
