package lessons.lesson02

class RomanNum(private val romanNum: String) : Number(), Comparable<RomanNum> {
    private val intValue: Int

    constructor(number: Number) : this(toRoman(number).getRomanValue())

    companion object {
        const val MIN_INT_VALUE: Int = 1
        const val MAX_INT_VALUE: Int = 3999
        const val MIN_ROMAN_VALUE: String = "I"
        const val MAX_ROMAN_VALUE: String = "MMMCMXCIX"
        private val romanSymbolsToValues: Map<String, Int> = mapOf(
            "M" to 1000,
            "CM" to 900,
            "D" to 500,
            "CD" to 400,
            "C" to 100,
            "XC" to 90,
            "L" to 50,
            "XL" to 40,
            "X" to 10,
            "IX" to 9,
            "V" to 5,
            "IV" to 4,
            "I" to 1
        )
        private val romanSymbols: Array<String> = arrayOf(
            "M",
            "CM",
            "D",
            "CD",
            "C",
            "XC",
            "L",
            "XL",
            "X",
            "IX",
            "V",
            "IV",
            "I"
        )
        private val romanValues: Array<Int> = arrayOf(
            1000,
            900,
            500,
            400,
            100,
            90,
            50,
            40,
            10,
            9,
            5,
            4,
            1
        )

        fun toRoman(number: Number): RomanNum {

            var intNumber: Int = number.toInt()
            var result = ""
            var i = 0
            while (intNumber > 0) {
                try {
                    result += romanSymbols[i].repeat((intNumber / romanValues[i]))
                } catch (_: Exception) {
                }
                intNumber %= romanValues[i]
                i++
            }
            return RomanNum(result)
        }
    }

    init {
        RomanValidator(romanNum).validate()
        intValue = toInt()
    }

    fun getRomanValue(): String {
        return romanNum
    }

    fun getIntValue(): Int {
        return intValue
    }

    override fun toByte(): Byte {
        return intValue.toByte()
    }

    override fun toDouble(): Double {
        return intValue.toDouble()
    }

    override fun toFloat(): Float {
        return intValue.toFloat()
    }

    override fun toInt(): Int {
        var result = 0
        var index = 0
        while (index < romanNum.length) {
            val subNum: String =
                if (index == romanNum.length - 1) romanNum[index].toString() else romanNum[index].toString() + romanNum[index + 1].toString()
            if (romanSymbolsToValues.contains(subNum)) {
                result += (romanSymbolsToValues[subNum] ?: 0)
                index++
            } else {
                result += (romanSymbolsToValues[romanNum[index].toString()] ?: 0)
            }
            index++
        }
        return result
    }

    override fun toLong(): Long {
        return intValue.toLong()
    }

    override fun toShort(): Short {
        return intValue.toShort()
    }

    override fun compareTo(other: RomanNum): Int {
        return intValue.compareTo(other.toInt())
    }

    private fun resultValidate(result: Int): RomanNum {
        return if ((result > MIN_INT_VALUE) and (result < MAX_INT_VALUE))
            result.toRoman()
        else
            throw IllegalArgumentException("Result out of range($MIN_ROMAN_VALUE..$MAX_ROMAN_VALUE)")
    }

    operator fun plus(value: Number): RomanNum {
        val result = this.intValue + value.toInt()
        return if ((result > MIN_INT_VALUE) and (result < MAX_INT_VALUE))
            result.toRoman()
        else
            throw IllegalArgumentException("Result of range($MIN_ROMAN_VALUE..$MAX_ROMAN_VALUE)")
    }

    operator fun minus(value: Number): RomanNum {
        val result = this.intValue - value.toInt()
        return resultValidate(result)
    }

    operator fun div(value: Number): RomanNum {
        val result = (this.intValue.toDouble() / value.toDouble()).toInt()
        return resultValidate(result)
    }

    operator fun times(value: Number): RomanNum {
        val result = (this.intValue.toDouble() * value.toDouble()).toInt()
        return resultValidate(result)
    }

    operator fun rem(value: Number): RomanNum {
        val result = (this.intValue.toDouble() % value.toDouble()).toInt()
        return resultValidate(result)
    }

    operator fun inc(): RomanNum {
        val result = this.intValue + 1
        return resultValidate(result)
    }

    operator fun dec(): RomanNum {
        val result = this.intValue - 1
        return resultValidate(result)
    }

    override fun hashCode(): Int {
        var result = romanNum.hashCode()
        result = 31 * result + intValue
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RomanNum

        return romanNum == other.romanNum
    }
}

fun Int.toRoman(): RomanNum {
    return RomanNum.toRoman(this)
}

fun Byte.toRoman(): RomanNum {
    return this.toInt().toRoman()
}

fun Double.toRoman(): RomanNum {
    return this.toInt().toRoman()
}

fun Float.toRoman(): RomanNum {
    return this.toInt().toRoman()
}

fun Long.toRoman(): RomanNum {
    return this.toInt().toRoman()
}

fun Short.toRoman(): RomanNum {
    return this.toInt().toRoman()
}