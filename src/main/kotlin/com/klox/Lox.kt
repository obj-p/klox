package com.klox

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Scanner

object Lox {
    private const val USAGE = "Usage: klox [script]"

    @JvmStatic fun main(args: Array<String>) {
        if (args.size > 1) {
            println(USAGE)
        } else if (args.size == 1) {
            runFile(args.first())
        } else {
            runPrompt()
        }
    }

    private fun run(source: String) {
        val scanner = Scanner(source)
        val tokens = scanner.tokens()

        for (token in tokens) {
            println(token)
        }
    }

    private fun runFile(path: String) {
        val bytes = Files.readAllBytes(Paths.get(path))

        run(String(bytes, Charset.defaultCharset()))
    }

    private fun runPrompt() {
        val input = InputStreamReader(System.`in`)
        val reader = BufferedReader(input)

        while (true) {
            print(">  ")
            val line = reader.readLine()

            line?.let(::run)
        }
    }
}

