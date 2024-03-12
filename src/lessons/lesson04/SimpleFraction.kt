package lessons.lesson04

import myTools.Validator
import kotlin.math.abs
import kotlin.math.absoluteValue


class SimpleFraction(
    fractionString: String
) : Number(), Comparable<Number> {
    private val numerator: Int
    private val denominator: Int
    private val fractionParser: SimpleFractionParser = SimpleFractionParser.Base()
    private val fractionConverter: SimpleFractionConverter = SimpleFractionConverter.Base()
    init {
        SimpleFractionValidator(fractionString).validate()


        val shortenFractionString = fractionConverter.shortenFraction(fractionString)

        numerator = fractionParser.takeNumerator(shortenFractionString)
        denominator = fractionParser.takeDenominator(shortenFractionString)
    }

    constructor(number: Number, fractionConverter: SimpleFractionConverter = SimpleFractionConverter.Base()) :
            this(fractionConverter.toFractional(number))

    companion object {


    }

    override fun compareTo(other: Number): Int = this.toDouble().compareTo(other.toDouble())

    override fun toByte(): Byte = toInt().toByte()

    override fun toDouble(): Double = numerator.toDouble() / denominator.toDouble()

    override fun toFloat(): Float = toDouble().toFloat()

    override fun toInt(): Int = toDouble().toInt()

    override fun toLong(): Long = toDouble().toLong()

    override fun toShort(): Short = toInt().toShort()

    override fun toString(): String = "$numerator/$denominator"


    operator fun plus(other: Number): SimpleFraction {
        val otherSimpleFraction = SimpleFraction(other)

        val denominator = this.denominator * otherSimpleFraction.denominator
        val numerator =
            this.numerator * otherSimpleFraction.denominator + otherSimpleFraction.numerator * this.denominator
        return SimpleFraction("$numerator/$denominator")
    }


    operator fun minus(other: Number): SimpleFraction {
        val otherSimpleFraction = SimpleFraction(other)

        val denominator = this.denominator * otherSimpleFraction.denominator
        val numerator =
            this.numerator * otherSimpleFraction.denominator - otherSimpleFraction.numerator * this.denominator
        return SimpleFraction("$numerator/$denominator")
    }

    operator fun div(other: Number): SimpleFraction {
        val otherSimpleFraction = SimpleFraction(other)

        val numerator = this.numerator * otherSimpleFraction.denominator
        val denominator = this.denominator * otherSimpleFraction.numerator
        return SimpleFraction("$numerator/$denominator")
    }

    operator fun times(other: Number): SimpleFraction {
        val otherSimpleFraction = SimpleFraction(other)

        val numerator = this.numerator * otherSimpleFraction.numerator
        val denominator = this.denominator * otherSimpleFraction.denominator
        return SimpleFraction("$numerator/$denominator")
    }

    operator fun unaryPlus(): SimpleFraction = SimpleFraction(this)

    operator fun unaryMinus(): SimpleFraction = (SimpleFraction(this) * SimpleFraction("-1"))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SimpleFraction

        return this.toDouble() == other.toDouble()
    }

    override fun hashCode(): Int {
        var result = numerator
        result = 31 * result + denominator
        return result
    }

    class SimpleFractionValidator(private val fractionalNumber: String) : Validator {
        companion object {
            val regexValidator = Regex("""-?\d+(/-?\d*)?""")
        }

        override fun validate(): Boolean {
            if (!regexValidator.matches(fractionalNumber)) throw IllegalArgumentException("Not fraction in Integers")
            else return true
        }
    }
}
interface SimpleFractionParser{
    fun takeNumerator(fractionString: String): Int
    fun takeDenominator(fractionString: String): Int
    class Base: SimpleFractionParser{
        override fun takeNumerator(fractionString: String): Int{
            val numerator: Int
            try {
                numerator = fractionString.substringBefore("/").toInt()
            } catch (_: Exception) {
                throw (IllegalArgumentException("Numerator out of range"))
            }
            return numerator
        }
        override fun takeDenominator(fractionString: String): Int{
            val denominator: Int
            try {
                denominator = if (fractionString.contains("/")) fractionString.substringAfter("/").toInt() else 1
            } catch (_: Exception) {
                throw (IllegalArgumentException("Denominator out of range"))
            }
            if (denominator == 0) throw IllegalArgumentException("Denominator is zero")
            return denominator
        }
    }
}
interface SimpleFractionConverter{
    fun toFractional(number: Number): String
    fun shortenFraction(fractionString: String): String
    class Base(private val fractionParser: SimpleFractionParser.Base = SimpleFractionParser.Base()) : SimpleFractionConverter{
        override fun toFractional(number: Number): String {
            if (number is SimpleFraction) return number.toString()

            var numerator = number.toDouble()
            var denominator = 1.0

            while ((numerator % 1 != 0.0) and (abs(numerator) * 10 < Int.MAX_VALUE) and (abs(denominator) * 10 < Int.MAX_VALUE)) {
                numerator *= 10
                denominator *= 10
            }
            return shortenFraction(numerator.toInt().toString() + "/" + denominator.toInt())
        }


        override fun shortenFraction(fractionString: String): String {
            val numerator = fractionParser.takeNumerator(fractionString)
            val denominator = fractionParser.takeDenominator(fractionString)

            var gcd = getGreatestCommonDivisor(numerator.toInt(), denominator.toInt()).absoluteValue

            if ((denominator < 0))
                gcd *= -1

            return (numerator / gcd).toString() + "/" + (denominator / gcd)
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
}