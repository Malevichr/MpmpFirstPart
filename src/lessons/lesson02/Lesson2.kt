package lessons.lesson02

import MyTools.MyReader


/**
 * Second Lesson 13.02.2022
 */
class Lesson2(private val taskNumber: Int) {
    fun start() {
        when (taskNumber) {
            1 -> task1()
            2 -> taskRomanClass()
        }
    }

    private fun task1() {
        val n = MyReader.intInput()
        val A = MutableList(n) { 0 }

        for (i in 0..<n) {
            A[i] = MyReader.intInput()
        }
        println(A)
        val B = MutableList(n) { 0.0 }
        for (i in 0..<n) {
            var new = 0.0
            var count = 0.0
            for (j in 0..<5) {
                try {
                    new += A[i - 2 + j].toDouble()
                    count++
                } catch (_: Exception) {
                }
            }
            B[i] = new / count
        }
        println(B)
    }
    private fun taskRomanClass() {
        println(RomanNum(RomanNum.MAX_ROMAN_VALUE).toInt())
    }
}