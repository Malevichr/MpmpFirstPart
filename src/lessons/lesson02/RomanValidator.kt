package lessons.lesson02

interface Validator {
    fun validate(): Boolean
}

class RomanValidator(private val romanNumber: String) : Validator {
    companion object {
        val regexValidator = Regex("""M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})${'$'}""")
    }

    override fun validate(): Boolean {
        if (!regexValidator.matches(romanNumber))
            throw IllegalArgumentException("Not roman number")
        else
            return true
    }


}