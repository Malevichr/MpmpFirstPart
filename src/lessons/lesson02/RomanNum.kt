package lessons.lesson02

import myTools.Validator

class RomanNum(number: Number) : Number(), Comparable<RomanNum> {
    val intValue:Int
    init {
        RomanNumIntValidator(number).validate()
        intValue = number.toInt()
    }
    constructor(romanNum: String) : this(toIntFromRoman(romanNum))

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

        private fun toRomanString(number: Number): String {
            var intNumber: Int = number.toInt()

            if ((intNumber < MIN_INT_VALUE) or (intNumber > MAX_INT_VALUE))
                throw IllegalArgumentException("Out of range[$MIN_INT_VALUE..$MAX_INT_VALUE]")

            var result = ""
            var i = 0
            while (intNumber > 0) {
                try {
                    result += romanSymbols[i].repeat((intNumber / romanValues[i]))
                } catch (_: Exception) { }

                intNumber %= romanValues[i]
                i++
            }
            return result
        }
        private fun toIntFromRoman(romanNum: String): Int{
            RomanStringValidator(romanNum).validate()
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
        return intValue
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
        RomanNumIntValidator(result).validate()
        return RomanNum(result)
    }

    operator fun plus(value: Number): RomanNum {
        val result = this.intValue + value.toInt()
        return resultValidate(result)
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
        return intValue.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RomanNum

        return intValue == other.intValue
    }

    override fun toString(): String {
        return toRomanString(intValue)
    }
    class RomanStringValidator(private val romanNumber: String) : Validator {
        companion object {
            val regexValidator = Regex("""M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})${'$'}""")
        }

        override fun validate(): Boolean {
            if (!regexValidator.matches(romanNumber))
                throw IllegalArgumentException("Not roman number")
            else
                return true
        }
    }
    class RomanNumIntValidator(private val intValue: Number) : Validator{
        override fun validate(): Boolean {
            return if ((intValue.toInt() >= MIN_INT_VALUE) and (intValue.toInt() <= MAX_INT_VALUE))
                true
            else
                throw IllegalArgumentException("Number out of range [$MIN_ROMAN_VALUE..$MAX_ROMAN_VALUE]")
        }
    }
}

fun Int.toRoman(): RomanNum {
    return RomanNum(this)
}

fun Byte.toRoman(): RomanNum {
    return RomanNum(this)
}

fun Double.toRoman(): RomanNum {
    return RomanNum(this)
}

fun Float.toRoman(): RomanNum {
    return RomanNum(this)
}

fun Long.toRoman(): RomanNum {
    return RomanNum(this)
}

fun Short.toRoman(): RomanNum {
    return RomanNum(this)
}