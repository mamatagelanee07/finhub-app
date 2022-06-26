package com.andigeeky.finnhub.data.temp

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ParametrisedAlgorithmTests(
    private val cardNumber : String,
    private val expected : Boolean,

) {

    @Test
    fun `given card number when check them then should return valid or invalid`() {
        Assert.assertEquals(expected, Exercises.luhnAlgorithm(cardNumber))
    }

    private companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} - Raw Input '{0}' and expecting '{1}'")
        fun data() = listOf(
            arrayOf("79927398713", true),
            arrayOf("374245455400126", true),
            arrayOf("378282246310005", true),
            arrayOf("4242424242424242", true),
            arrayOf("6250941006528599", true),
            arrayOf("5555555555558726", true),
            arrayOf("5555555555558726", true),
            arrayOf("   ", false),
            arrayOf("374245455499126", false),
        )
    }
}