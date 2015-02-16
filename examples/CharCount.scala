package com.anowell.servur.examples;

object CharCount {
    def apply(input: String): Int = {
        println(s"DEBUG: counting chars in: $input")
        val count = input.length
        println(s"DEBUG: returning $count")
        count
    }
}
