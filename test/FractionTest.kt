import lessons.lesson02.RomanNum
import lessons.lesson04.Fraction
import lessons.lesson04.SimpleFraction
import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

class FractionTest{
    @Test
    fun toStringTest(){
        val fractionFirst = Fraction(RomanNum("IV"), 23)
        val fractionSecond = Fraction(2.5, SimpleFraction("11/2"))
        val complexFraction = Fraction(fractionFirst, fractionSecond)
        assertEquals("( ( IV / 23 ) / ( 2.5 / 11/2 ) )", complexFraction.toString())
    }
}