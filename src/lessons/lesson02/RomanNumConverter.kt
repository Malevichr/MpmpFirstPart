package lessons.lesson02

interface RomanNumConverter{
    fun toRomanString(number: Number): String
    fun toIntFromRoman(romanString: String): Int

    class Base: RomanNumConverter{
        companion object{
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
        }

        override fun toRomanString(number: Number): String {
            var intNumber: Int = number.toInt()

            if ((intNumber < RomanNum.MIN_INT_VALUE) or (intNumber > RomanNum.MAX_INT_VALUE))
                throw IllegalArgumentException("Out of range[${RomanNum.MIN_INT_VALUE}..${RomanNum.MAX_INT_VALUE}]")

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
        override fun toIntFromRoman(romanString: String): Int{
            RomanNum.RomanStringValidator(romanString).validate()
            var result = 0
            var index = 0
            while (index < romanString.length) {
                val subNum: String =
                    if (index == romanString.length - 1) romanString[index].toString() else romanString[index].toString() + romanString[index + 1].toString()
                if (romanSymbolsToValues.contains(subNum)) {
                    result += (romanSymbolsToValues[subNum] ?: 0)
                    index++
                } else {
                    result += (romanSymbolsToValues[romanString[index].toString()] ?: 0)
                }
                index++
            }
            return result
        }
    }
}