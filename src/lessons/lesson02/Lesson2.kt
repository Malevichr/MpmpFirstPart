package lessons.lesson02

import myTools.MyReader


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
        val arr = MutableList(n) { 0 }

        for (i in 0..<n) {
            arr[i] = MyReader.intInput()
        }
        println(arr)
        val bArr = MutableList(n) { 0.0 }
        for (i in 0..<n) {
            var new = 0.0
            var count = 0.0
            for (j in 0..<5) {
                try {
                    new += arr[i - 2 + j].toDouble()
                    count++
                } catch (_: Exception) {
                }
            }
            bArr[i] = new / count
        }
        println(bArr)
    }
    private fun taskRomanClass() {
        val num = MyReader.intInput()
        println(num.toRoman().toString())
        println(num.toRoman().toInt())
    }
}