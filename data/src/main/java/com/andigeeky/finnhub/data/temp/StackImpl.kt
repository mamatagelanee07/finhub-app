package com.andigeeky.finnhub.data.temp

import java.lang.RuntimeException

class StackImpl<T : Comparable<T>> : Stack<T> {
    private val elements = mutableListOf<T>()
    private var _min: T? = null

    override fun push(element: T) {
        elements.add(element)
        _min = elements.minOf { it }
    }

    override val top: T
        get() = if (elements.isEmpty()) throw RuntimeException("Stack is Empty")
        else elements.last()

    override fun pop() {
        if (elements.isEmpty()) throw RuntimeException("Stack is Empty")
        else {
            elements.removeLast()
            _min = elements.minOf { it }
        }
    }

    override val min: T
        get() = if (elements.isEmpty()) throw RuntimeException("Stack is Empty") else _min ?: throw RuntimeException("min is null")
}