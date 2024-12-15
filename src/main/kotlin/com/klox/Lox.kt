package com.klox

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Scanner
import kotlin.system.exitProcess

class Lox {
    private var hadError = false

    private fun error(line: Int, message: String) {
        report(line, "", message)
    }

    companion object {
        private const val EX_DATAERR = 65
        private const val EX_USAGE = 64
        private const val USAGE = "Usage: klox [script]"

        @JvmStatic
        fun main(args: Array<String>) {
            val lox = Lox()

            if (args.size > 1) {
                println(USAGE)
                exitProcess(EX_USAGE)
            } else if (args.size == 1) {
                lox.runFile(args.first())
            } else {
                lox.runPrompt()
            }
        }
    }

    private fun report(line: Int, where: String, message: String) {
        System.err.println("[line $line] Error$where: $message")
        hadError = true
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

        if (hadError) exitProcess(EX_DATAERR)
    }

    private fun runPrompt() {
        val input = InputStreamReader(System.`in`)
        val reader = BufferedReader(input)

        while (true) {
            print(">  ")
            val line = reader.readLine()

            line?.let(::run)
            hadError = false
        }
    }
}

