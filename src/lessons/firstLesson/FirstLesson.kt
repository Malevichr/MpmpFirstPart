package lessons.firstLesson

import MyTools.MyReader


class FirstLesson (private val taskNumber: Int) {
    fun start(){
        when (taskNumber){
            1-> task1()
        }
    }
    private fun task1(){
        println("Enter number")
        val number:Int = MyReader.intInput()
        println("Enter base")
        val base = MyReader.intInput()
        println("Enter new base")
        val newBase = MyReader.intInput()
        val converter = Converter.IntConverter(number, base).convert(newBase)

        println("Value = " + converter.getValue() + " Base = " + converter.getBase())
    }
}