package lessons.lesson04

class Fraction(
    private val numerator: Number,
    private val denominator: Number,
) : Number(), Comparable<Number> {
    override fun compareTo(other: Number): Int = this.toDouble().compareTo(other.toDouble())

    override fun toByte(): Byte = toInt().toByte()

    override fun toDouble(): Double = numerator.toDouble() / denominator.toDouble()

    override fun toFloat(): Float = toDouble().toFloat()

    override fun toInt(): Int = toDouble().toInt()

    override fun toLong(): Long = toDouble().toLong()

    override fun toShort(): Short = toInt().toShort()

    operator fun plus(other: Number) = this.toDouble() + other.toDouble()
    operator fun minus(other: Number) = this.toDouble() - other.toDouble()
    operator fun times(other: Number) = this.toDouble() * other.toDouble()
    operator fun div(other: Number) = this.toDouble() / other.toDouble()
    operator fun unaryPlus() = this.toDouble()
    operator fun unaryMinus() = -(this.toDouble())


    override fun toString(): String = "( $numerator / $denominator )"
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Fraction

        if (numerator != other.numerator) return false
        if (denominator != other.denominator) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numerator.hashCode()
        result = 31 * result + denominator.hashCode()
        return result
    }
}