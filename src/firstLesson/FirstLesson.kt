package firstLesson

import Reader
import kotlin.math.pow



class IntConverter(private val number: Int, private val base: Int = 10) {
    fun getValue() = number
    fun getBase() = base
    init {
        baseValidator()
    }
    private fun getDecimal(number: Int, base: Int):IntConverter{
        val numberList: List<Int> = number.toString().reversed().map(Character::getNumericValue)
        var result = 0
        var i = 0
        numberList.forEach{
            result += (it * base.toDouble().pow(i)).toInt()
            ++i
        }
        return IntConverter(result, base)
    }
    private fun fromDecimal(n: Int, base: Int):String{
        return if (n != 0)
            fromDecimal(n / base, base) + n % base
        else
            ""
    }
    fun convert(base: Int): IntConverter {
        baseValidator()
        val decimalNumber = getDecimal(number, this.base)
        return IntConverter(fromDecimal(decimalNumber.number, base).toInt(), base)
    }
    private fun baseValidator(): Boolean{
        if ((base < 2) or (base > 10))
            throw IllegalArgumentException("Base must be in [2..10]")
        return true
    }
}



class FirstLesson (private val taskNumber: Int) {
    fun start(){
        when (taskNumber){
            1-> task1()
        }
    }
    private fun task1(){
        val number = Reader.intInput()
        val base = Reader.intInput()
        val converter = IntConverter(number, base)
        println("Value = " + converter.convert(10).getValue() + " Base = " + converter.convert(10).getBase())
    }

}