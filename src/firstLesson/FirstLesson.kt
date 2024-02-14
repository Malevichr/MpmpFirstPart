package firstLesson

import Reader
import kotlin.math.pow

interface Converter{
    fun convert(base: Int):Converter
    fun getNumber():Int
    fun getBase(): Int
}

class IntConverter(private val number: Int, private val base: Int = 10) : Converter{
    override fun getNumber() = number
    override fun getBase() = base
    init {
        if ((base < 2) or (base > 10))
            throw IllegalArgumentException("Base must be in [2..10]")
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
        if (n != 0)
            return fromDecimal(n / base, base) + n % base
        else
            return ""
    }
    override fun convert(base: Int): IntConverter {
        val decimalNumber = getDecimal(number, this.base)
        return IntConverter(fromDecimal(decimalNumber.number, base).toInt(), base)
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
        println(converter.convert(10).getNumber())
    }

}