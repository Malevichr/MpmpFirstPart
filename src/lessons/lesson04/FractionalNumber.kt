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

    constructor(number: Number) : this(toFractional(number))

    companion object {
        private fun toFractional(number: Number): String {
            if (number is FractionalNumber)
                return number.toString()

            var numerator = number.toDouble()
            var denominator = 1.0

            while (numerator % 1 != 0.0) {
                numerator *= 10
                denominator *= 10
            }

            if ((Int.MIN_VALUE < numerator) or (numerator > Int.MAX_VALUE))
                throw IllegalArgumentException("Numerator out of range")
            if ((Int.MIN_VALUE < denominator) or (denominator > Int.MAX_VALUE))
                throw (IllegalArgumentException("Denominator out of range"))

            var gcd = getGreatestCommonDivisor(numerator.toInt(), denominator.toInt())

            if ((numerator < 0) and (denominator > 0))
                gcd *= -1

            return (numerator.toInt() / gcd).toString() + "/" + (denominator.toInt() / gcd)
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

    operator fun plus(other: Number): FractionalNumber {
        val otherFractionalNumber = FractionalNumber(other)

        val denominator = this.denominator * otherFractionalNumber.denominator
        val numerator =
            this.numerator * otherFractionalNumber.denominator + otherFractionalNumber.numerator * this.denominator
        return FractionalNumber("$numerator/$denominator").shortenFraction()
    }


    operator fun minus(other: Number): FractionalNumber {
        val otherFractionalNumber = FractionalNumber(other)

        val denominator = this.denominator * otherFractionalNumber.denominator
        val numerator =
            this.numerator * otherFractionalNumber.denominator - otherFractionalNumber.numerator * this.denominator
        return FractionalNumber("$numerator/$denominator").shortenFraction()
    }

    operator fun div(other: Number): FractionalNumber {
        val otherFractionalNumber = FractionalNumber(other)

        val numerator = this.numerator * otherFractionalNumber.denominator
        val denominator = this.denominator * otherFractionalNumber.numerator
        return FractionalNumber("$numerator/$denominator").shortenFraction()
    }

    operator fun times(other: Number): FractionalNumber {
        val otherFractionalNumber = FractionalNumber(other)

        val numerator = this.numerator * otherFractionalNumber.numerator
        val denominator = this.denominator * otherFractionalNumber.denominator
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

        return this.toDouble() == other.toDouble()
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