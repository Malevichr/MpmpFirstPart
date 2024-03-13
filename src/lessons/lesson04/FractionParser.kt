package lessons.lesson04

interface FractionParser {
    fun takeNumerator(fractionString: String): Int
    fun takeDenominator(fractionString: String): Int
    class Simple : FractionParser {
        override fun takeNumerator(fractionString: String): Int {
            val numerator: Int
            try {
                numerator = fractionString.substringBefore("/").toInt()
            } catch (_: Exception) {
                throw (IllegalArgumentException("Numerator out of range"))
            }
            return numerator
        }

        override fun takeDenominator(fractionString: String): Int {
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