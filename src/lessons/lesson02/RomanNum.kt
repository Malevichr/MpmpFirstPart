package lessons.lesson02

class RomanNum(private val romanNum: String) : Number(), Comparable<RomanNum> {
    private val intValue: Int

    companion object {
        public const val MIN_INT_VALUE: Int = 1
        public const val MAX_INT_VALUE: Int = 3999
        public const val MIN_ROMAN_VALUE: String = "I"
        public const val MAX_ROMAN_VALUE: String = "MMMCMXCIX"
        private val romanValues: Map<String, Int> = mapOf(
            "CM" to 900,
            "CD" to 400,
            "XC" to 90,
            "XL" to 40,
            "IX" to 9,
            "IV" to 4,
            "I" to 1,
            "V" to 5,
            "X" to 10,
            "L" to 50,
            "C" to 100,
            "D" to 500,
            "M" to 1000
        )
    }

    init {
        RomanValidator(romanNum).validate()
        intValue = toInt()
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
        var result: Int = 0
        var index = 0
        while(index < romanNum.length){
            val subNum: String = if (index == romanNum.length - 1) romanNum[index].toString() else romanNum[index].toString() + romanNum[index + 1].toString()
            if (romanValues.contains(subNum)){
                result += (romanValues[subNum] ?: 0)
                index++
            }
            else {
                result += (romanValues[romanNum[index].toString()] ?: 0)
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

    fun romanFromInt(intNumber: Int): RomanNum{
        val result = " sad"
        return RomanNum(result)
    }

}