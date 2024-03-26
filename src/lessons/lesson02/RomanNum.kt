package lessons.lesson02

import myTools.Validator

class RomanNum(
    number: Number,
    romanNumConverter: RomanNumConverter = RomanNumConverter.Base(),
) : Number(), Comparable<Number> {
    val intValue: Int
    private val romanNumConverter: RomanNumConverter

    init {
        RomanIntValidator(number).validate()
        intValue = number.toInt()
        this.romanNumConverter = romanNumConverter
    }

    constructor(
        romanNum: String,
        romanNumConverter: RomanNumConverter = RomanNumConverter.Base(),
    ) : this(romanNumConverter.toIntFromRoman(romanNum))

    companion object {
        const val MIN_INT_VALUE: Int = 1
        const val MAX_INT_VALUE: Int = 3999
        const val MIN_ROMAN_VALUE: String = "I"
        const val MAX_ROMAN_VALUE: String = "MMMCMXCIX"
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

    override fun compareTo(other: Number): Int {
        return intValue.compareTo(other.toDouble())
    }

    private fun resultValidate(result: Int): RomanNum {
        RomanIntValidator(result).validate()
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
        return romanNumConverter.toRomanString(intValue)
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

    class RomanIntValidator(private val intValue: Number) : Validator {
        override fun validate(): Boolean {
            return if ((intValue.toInt() >= MIN_INT_VALUE) and (intValue.toInt() <= MAX_INT_VALUE))
                true
            else
                throw IllegalArgumentException("Number out of range [$MIN_ROMAN_VALUE..$MAX_ROMAN_VALUE]")
        }
    }
}


