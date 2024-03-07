package tests

import lessons.lesson04.FractionalNumber
import org.testng.Assert.assertThrows
import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

@Test
class FractionalNumberTest {
    @Test
    fun intInitTest(){
        val fractionalNumber = FractionalNumber(5)
        assertEquals("5/1", fractionalNumber.toString())
    }
    @Test
    fun doubleInitTest1(){
        val fractionalNumber = FractionalNumber(5.5)
        assertEquals("11/2", fractionalNumber.toString())
    }
    @Test
    fun doubleInitTest2(){
        val fractionalNumber = FractionalNumber(-0.45)
        assertEquals("-9/20", fractionalNumber.toString())
    }
    @Test
    fun initWithExceptionTest1() {
        assertThrows(IllegalArgumentException::class.java) {
            FractionalNumber("1234УВЫА")
        }
    }

    @Test
    fun initWithExceptionTest2() {
        assertThrows(IllegalArgumentException::class.java) {
            FractionalNumber("1234/")
        }
    }

    @Test
    fun initWithExceptionTest3() {
        assertThrows(IllegalArgumentException::class.java) {
            FractionalNumber("/1234")
        }
    }

    @Test
    fun initWithExceptionTest4() {
        assertThrows(IllegalArgumentException::class.java) {
            FractionalNumber("-1234444444444444/14444444423445454")
        }
    }
    @Test
    fun initWithExceptionTest5() {
        assertThrows(IllegalArgumentException::class.java) {
            FractionalNumber("-0/0")
        }
    }
    @Test
    fun initWithExceptionTest6() {
        assertThrows(IllegalArgumentException::class.java) {
            FractionalNumber(1/3)
        }
    }
    fun initTest(){
        val fractionalNumber1 = FractionalNumber("4/5")
        val newFractionalNumber = FractionalNumber(fractionalNumber1)
        assertEquals(fractionalNumber1.toString(), newFractionalNumber.toString())
    }
    @Test
    fun toStringTest1() {
        val fractionalNumber = FractionalNumber("5/3")
        assertEquals("5/3", fractionalNumber.toString())
    }

    @Test
    fun toStringTest2() {
        val fractionalNumber = FractionalNumber("5")
        assertEquals("5/1", fractionalNumber.toString())
    }

    @Test
    fun toStringTest3() {
        val fractionalNumber = FractionalNumber("-5")
        assertEquals("-5/1", fractionalNumber.toString())
    }

    @Test
    fun shortenFractionTest1() {
        val fractionalNumber = FractionalNumber("5/10")
        val shortedFractionalNumber = fractionalNumber.shortenFraction()
        assertEquals("1/2", shortedFractionalNumber.toString())
    }

    @Test
    fun shortenFractionTest2() {
        val fractionalNumber = FractionalNumber("-4/24")
        val shortedFractionalNumber = fractionalNumber.shortenFraction()
        assertEquals("-1/6", shortedFractionalNumber.toString())
    }

    @Test
    fun shortenFractionTest3() {
        val fractionalNumber = FractionalNumber("30/-5")
        val shortedFractionalNumber = fractionalNumber.shortenFraction()
        assertEquals("-6/1", shortedFractionalNumber.toString())
    }

    @Test
    fun shortenFractionTest4() {
        val fractionalNumber = FractionalNumber("-12/-3")
        val shortedFractionalNumber = fractionalNumber.shortenFraction()
        assertEquals("4/1", shortedFractionalNumber.toString())
    }

    @Test
    fun toIntTest() {
        val fractionalNumber = FractionalNumber("-12/-3")
        assertEquals(4, fractionalNumber.toInt())
    }
    @Test
    fun toDoubleTest() {
        val fractionalNumber = FractionalNumber("-3/5")
        assertEquals(-0.6, fractionalNumber.toDouble())
    }
    @Test
    fun plusTest1(){
        val fractionalNumberFirst = FractionalNumber("1/2")
        val fractionalNumberSecond = FractionalNumber("1/3")
        assertEquals("5/6", (fractionalNumberFirst + fractionalNumberSecond).toString())
    }
    @Test
    fun plusTest2(){
        val fractionalNumberFirst = FractionalNumber("-1/2")
        val fractionalNumberSecond = FractionalNumber("1/3")
        assertEquals("-1/6", (fractionalNumberFirst + fractionalNumberSecond).toString())
    }
    fun minusTest(){
        val fractionalNumberFirst = FractionalNumber("1/2")
        val fractionalNumberSecond = FractionalNumber("1/6")
        assertEquals("1/3", (fractionalNumberFirst - fractionalNumberSecond).toString())
    }
    fun divTest(){
        val fractionalNumberFirst = FractionalNumber("1/2")
        val fractionalNumberSecond = FractionalNumber("1/6")
        assertEquals("3/1", (fractionalNumberFirst / fractionalNumberSecond).toString())
    }
    fun timesTest(){
        val fractionalNumberFirst = FractionalNumber("4/2")
        val fractionalNumberSecond = FractionalNumber("1/6")
        assertEquals("1/3", (fractionalNumberFirst * fractionalNumberSecond).toString())
    }
    fun unaryMinusTest() {
        val fractionalNumber = FractionalNumber("4/2")
        assertEquals("-2/1", (-fractionalNumber).toString())
    }
    fun equalsTest1(){
        val fractionalNumberFirst = FractionalNumber("4/2")
        val fractionalNumberSecond = FractionalNumber("8/4")
        assertEquals(true, fractionalNumberFirst == fractionalNumberSecond)
    }
}