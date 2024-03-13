package lessons.lesson04

import myTools.Validator


class SimpleFraction(
    fractionString: String,
    fractionConverter: FractionConverter = FractionConverter.Simple(),
    fractionParser: FractionParser = FractionParser.Simple()
) : Number(), Comparable<Number> {
    private val numerator: Int
    private val denominator: Int
    private val fractionParser: FractionParser
    private val fractionConverter: FractionConverter

    init {
        SimpleFractionValidator(fractionString).validate()
        this.fractionParser = fractionParser
        this.fractionConverter = fractionConverter

        val shortenFractionString = this.fractionConverter.shortenFraction(fractionString)

        numerator = this.fractionParser.takeNumerator(shortenFractionString)
        denominator = this.fractionParser.takeDenominator(shortenFractionString)
    }

    constructor(
        number: Number,
        fractionConverter: FractionConverter = FractionConverter.Simple(),
        fractionParser: FractionParser = FractionParser.Simple()
    ) : this(
        fractionConverter.toFractional(number),
        fractionConverter,
        fractionParser
    )

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



