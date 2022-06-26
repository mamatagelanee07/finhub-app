package com.andigeeky.finnhub.data.temp

interface Stack<T> {
    /**
     * Pushes element on stack
     * return : 1 if successfully pushed or 0
     */
    fun push(element : T)

    fun pop()

    val top : T

    val min : T
}