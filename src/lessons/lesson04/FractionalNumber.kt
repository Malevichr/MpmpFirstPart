package lessons.lesson04

import lessons.lesson02.Validator


class FractionalNumber(fractional: String) : Number(), Comparable<FractionalNumber> {
    private val numerator: Int
    private val denominator: Int

    init {
        FractionalNumberValidator(fractional).validate()

        try {
            numerator = fractional.substringBefore("/").toInt()
        } catch (_: Exception) {
            throw (IllegalArgumentException("Numerator out of range"))
        }
        try {
            this.denominator = if (fractional.contains("/")) fractional.substringAfter("/").toInt() else 1
        } catch (_: Exception) {
            throw (IllegalArgumentException("Denominator out of range"))
        }
        if (denominator == 0)
            throw IllegalArgumentException("Denominator is zero")

    }

    constructor(fractionalNumber: FractionalNumber) : this(fractionalNumber.toString())
    constructor(number: Number) : this(toFractional(number))

    companion object {
        private fun toFractional(number: Number): String{
            var numerator = number.toDouble()
            var denominator = 1

            while (numerator % 1 != 0.0){
                numerator *= 10
                denominator *= 10
            }
            var gcd = getGreatestCommonDivisor(numerator.toInt(), denominator)

            if ((numerator < 0) and (denominator > 0))
                gcd *= -1

            return (numerator.toInt() / gcd).toString() + "/" + (denominator / gcd)
        }

        private fun getGreatestCommonDivisor(a: Int, b: Int): Int {
            var num1 = a
            var num2 = b
            while (num2 != 0) {
                val temp = num2
                num2 = num1 % num2
                num1 = temp
            }
            return num1
        }
    }

    override fun compareTo(other: FractionalNumber): Int {
        return this.toDouble().compareTo(other.toDouble())
    }

    override fun toByte(): Byte = (numerator / denominator).toByte()

    override fun toDouble(): Double = numerator.toDouble() / denominator


    override fun toFloat(): Float = numerator.toFloat() / denominator


    override fun toInt(): Int = numerator / denominator

    override fun toLong(): Long = (numerator / denominator).toByte().toLong()

    override fun toShort(): Short = (numerator / denominator).toByte().toShort()

    override fun toString(): String {
        return "$numerator/$denominator"
    }

    fun shortenFraction(): FractionalNumber {
        var gcd = getGreatestCommonDivisor(numerator, denominator)
        if ((numerator < 0) and (denominator > 0))
            gcd *= -1
        return FractionalNumber((numerator / gcd).toString() + "/" + (denominator / gcd))
    }

    operator fun plus(other: FractionalNumber): FractionalNumber {
        val denominator = this.denominator * other.denominator
        val numerator = this.numerator * other.denominator + other.numerator * this.denominator
        return FractionalNumber("$numerator/$denominator").shortenFraction()
    }


    operator fun minus(other: FractionalNumber): FractionalNumber {
        val denominator = this.denominator * other.denominator
        val numerator = this.numerator * other.denominator - other.numerator * this.denominator
        return FractionalNumber("$numerator/$denominator").shortenFraction()
    }

    operator fun div(other: FractionalNumber): FractionalNumber {
        val numerator = this.numerator * other.denominator
        val denominator = this.denominator * other.numerator
        return FractionalNumber("$numerator/$denominator").shortenFraction()
    }

    operator fun times(other: FractionalNumber): FractionalNumber {
        val numerator = this.numerator * other.numerator
        val denominator = this.denominator * other.denominator
        return FractionalNumber("$numerator/$denominator").shortenFraction()
    }

    operator fun unaryPlus(): FractionalNumber {
        return FractionalNumber(this)
    }

    operator fun unaryMinus(): FractionalNumber {
        return (FractionalNumber(this) * FractionalNumber("-1")).shortenFraction()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FractionalNumber

        if (this.shortenFraction().numerator != other.shortenFraction().numerator) return false
        if (this.shortenFraction().denominator != other.shortenFraction().denominator) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numerator
        result = 31 * result + denominator
        return result
    }


    class FractionalNumberValidator(private val fractionalNumber: String) : Validator {
        companion object {
            val regexValidator = Regex("""-?\d+(/-?\d*)?""")
        }

        override fun validate(): Boolean {
            if (!regexValidator.matches(fractionalNumber))
                throw IllegalArgumentException("Not fraction in Integers")
            else
                return true
        }

    }
}