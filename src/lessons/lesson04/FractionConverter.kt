package lessons.lesson04

import kotlin.math.abs
import kotlin.math.absoluteValue

interface FractionConverter {
    fun toFractional(number: Number): String
    fun shortenFraction(fractionString: String): String
    class Simple(private val fractionParser: FractionParser.Simple = FractionParser.Simple()) :
        FractionConverter {
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

            var gcd = getGreatestCommonDivisor(numerator, denominator).absoluteValue

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