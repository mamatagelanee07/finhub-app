package com.andigeeky.finnhub.data.temp

class StringFormatter {
    // 2 -> "two"
// 12 -> "twelve"
// 123 -> "one hundred and twenty three"
// To execute Kotlin code, please define a top level function named main

    fun main() {
        for (i in 1..5) println("Hello, World!")
    }

    fun getNumberString(number: Int): String? {
        // if(number < 0 || number > 999) throw RuntimeError("Invalid number")

        return when (number) {
            in 0..19 -> one_to_19[number]
            in 20..99 -> {
                val tensDigits = number / 10 // 90
                val singleDigits = number % 10 // 9
                two_to_9_tys[tensDigits] + one_to_19[singleDigits]
            }
            in 100..999 -> {
                // 678
                val hundreadsPrefix = number / 100 //6
                val tensPrefix = number % 100 //78
                one_to_19[hundreadsPrefix] + "hundread s" + getNumberString(tensPrefix)
            }
            in 1000..9999 -> {
                // 7898
                val tPrefix = number / 1000 //7
                val hPrefix = number % 100 //78
                one_to_19[tPrefix] + "thousand" + getNumberString(hPrefix)
            }
            else -> ""
        }
    }
}

val one_to_19 = mapOf(
    0 to "zero",
    1 to "one",
    2 to "two",
    19 to "ninteen"
)

val two_to_9_tys = mapOf(
    2 to "twenty",
    3 to "thirty",
    9 to "ninty"
)

/**
 * one - twenty
 * twenty - one to nine
 * thirty
 * hundred
 * two hundred
 * three hundred
 * nine hundred
 * could go up to 999
 **/