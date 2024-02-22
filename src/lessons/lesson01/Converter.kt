package lessons.lesson01

import kotlin.math.pow

interface Converter {
    fun convert(newBase: Int): Converter
    fun getValue(): String
    fun getBase(): String
    class IntConverter(private val number: Int, private val base: Int = 10) : Converter {
        override fun getValue() = number.toString()
        override fun getBase() = base.toString()

        init {
            baseValidator(base)
        }

        private fun baseValidator(base: Int): Boolean {
            if ((base < 2) or (base > 10))
                throw IllegalArgumentException("Base must be in [2..10]")
            return true
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

        override fun convert(newBase: Int): Converter {
            baseValidator(newBase)
            val decimalNumber = getDecimal(number, this.base)
            return IntConverter(fromDecimal(decimalNumber.number, newBase).toInt(), newBase)
        }
    }
}
