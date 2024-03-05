package lessons.lesson04

import lessons.lesson02.Validator

class FractionalNumberValidator(val fractionalNumber: String): Validator{
    companion object {
        val regexValidator = Regex("""\d+(/\d*)?""")
    }

    override fun validate(): Boolean {
        if (!regexValidator.matches(fractionalNumber))
            throw IllegalArgumentException("Not fraction in Integers")
        else
            return true
    }

}
class FractionalNumber(fractionalNumber: String): Number(), Comparable<FractionalNumber> {
    private val numerator: Int
    private val denominator: Int

    init {
        FractionalNumberValidator(fractionalNumber).validate()

        this.numerator = fractionalNumber.substringBefore("/").toInt()
        this.denominator = if (fractionalNumber.contains("/")) fractionalNumber.substringAfter("/").toInt() else 1
    }
    companion object{


    }
    override fun compareTo(other: FractionalNumber): Int {
        TODO("Not yet implemented")
    }

    override fun toByte(): Byte {
        TODO("Not yet implemented")
    }

    override fun toDouble(): Double {
        TODO("Not yet implemented")
    }

    override fun toFloat(): Float {
        TODO("Not yet implemented")
    }

    override fun toInt(): Int {
        TODO("Not yet implemented")
    }

    override fun toLong(): Long {
        TODO("Not yet implemented")
    }

    override fun toShort(): Short {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "$numerator/$denominator"
    }
}