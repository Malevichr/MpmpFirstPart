import lessons.lesson02.RomanNum
import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test


class RomanNumTest {
    @Test
    fun intToRomanTest(){
        for(i in 1..3999){
            assertEquals(i, RomanNum(i).toInt())
        }
    }
    @Test
    fun doubleToRomanTest(){
        for(i in 1..3999){
            assertEquals(i.toDouble(), RomanNum(i).toDouble())
        }
    }
    @Test
    fun romanPlusTest(){
        for (i in 1..1000){
            assertEquals(RomanNum((i + 2*i)).toString(), (RomanNum(i) + 2*i).toString())
        }
    }
    @Test
    fun romanMinusTest(){
        for (i in 1..1000){
            assertEquals(RomanNum(3*i - i).toString(), RomanNum((3*i) - i).toString())
        }
    }
    @Test
    fun equalsTest(){
        for (i in 1..3999){
            assertEquals(RomanNum(i) == RomanNum(i), true)
        }
    }
}