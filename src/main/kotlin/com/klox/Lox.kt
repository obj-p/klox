package com.klox

class Lox {
    val greeting: String get() = "Hello World!"
}

fun main() {
    println(Lox().greeting)
}