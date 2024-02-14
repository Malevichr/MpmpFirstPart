package firstLesson

import Reader
import kotlin.math.pow


interface Converter {
    fun convert(base: Number):Converter
    fun getValue():String
    fun getBase():String
    class IntConverter(private val number: Int, private val base: Int = 10) : Converter{
        override fun getValue() = number.toString()
        override fun getBase() = base.toString()

        init {
            baseValidator()
        }

        private fun getDecimal(number: Int, base: Int): IntConverter {
            val numberList: List<Int> = number.toString().reversed().map(Character::getNumericValue)
            var result = 0
            var i = 0
            numberList.forEach {
                result += (it * base.toDouble().pow(i)).toInt()
                ++i
            }
            return IntConverter(result, base)
        }

        private fun fromDecimal(n: Int, base: Int): String {
            return if (n != 0)
                fromDecimal(n / base, base) + n % base
            else
                ""
        }

        override fun convert(base: Number): Converter {
            baseValidator()
            val decimalNumber = getDecimal(number, this.base)
            return IntConverter(fromDecimal(decimalNumber.number, base.toInt()).toInt(), base.toInt())
        }

        private fun baseValidator(): Boolean {
            if ((base < 2) or (base > 10))
                throw IllegalArgumentException("Base must be in [2..10]")
            return true
        }
    }
}


class FirstLesson (private val taskNumber: Int) {
    fun start(){
        when (taskNumber){
            1-> task1()
        }
    }
    private fun task1(){
        println("Enter number")
        val number:Int = Reader.intInput()
        println("Enter base")
        val base = Reader.intInput()
        println("Enter new base")
        val newBase = Reader.intInput()
        val converter = Converter.IntConverter(number, base).convert(newBase)

        println("Value = " + converter.getValue() + " Base = " + converter.getBase())
    }
}