import lessons.lesson02.RomanNum
import lessons.lesson02.toRoman
import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test


class RomanNumTest {
    @Test
    fun intToRomanTest(){
        for(i in 1..3999){
            assertEquals(i, i.toRoman().toInt())
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
            assertEquals((i + 2*i).toRoman().toString(), (i.toRoman() + 2*i).toString())
        }
    }
    @Test
    fun romanMinusTest(){
        for (i in 1..1000){
            assertEquals((3*i - i).toRoman().toString(), ((3*i).toRoman() - i).toString())
        }
    }
    @Test
    fun equalsTest(){
        for (i in 1..3999){
            assertEquals(i.toRoman() == i.toRoman(), true)
        }
    }
}