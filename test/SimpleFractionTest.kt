import lessons.lesson04.SimpleFraction
import org.testng.Assert.assertThrows
import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test

@Test
class SimpleFractionTest {
    @Test
    fun intInitTest(){
        val simpleFraction = SimpleFraction(5)
        assertEquals("5/1", simpleFraction.toString())
    }
    @Test
    fun doubleInitTest1(){
        val simpleFraction = SimpleFraction(5.5)
        assertEquals("11/2", simpleFraction.toString())
    }
    @Test
    fun doubleInitTest2(){
        val simpleFraction = SimpleFraction(-0.45)
        assertEquals("-9/20", simpleFraction.toString())
    }
    @Test
    fun initWithExceptionTest1() {
        assertThrows(IllegalArgumentException::class.java) {
            SimpleFraction("1234УВЫА")
        }
    }

    @Test
    fun initWithExceptionTest2() {
        assertThrows(IllegalArgumentException::class.java) {
            SimpleFraction("1234/")
        }
    }

    @Test
    fun initWithExceptionTest3() {
        assertThrows(IllegalArgumentException::class.java) {
            SimpleFraction("/1234")
        }
    }

    @Test
    fun initWithExceptionTest4() {
        assertThrows(IllegalArgumentException::class.java) {
            SimpleFraction("-1234444444444444/14444444423445454")
        }
    }
    @Test
    fun initWithExceptionTest5() {
        assertThrows(IllegalArgumentException::class.java) {
            SimpleFraction("-0/0")
        }
    }
    fun initTest(){
        val simpleFraction1 = SimpleFraction("4/5")
        val newSimpleFraction = SimpleFraction(simpleFraction1)
        assertEquals(simpleFraction1.toString(), newSimpleFraction.toString())
    }
    @Test
    fun toStringTest1() {
        val simpleFraction = SimpleFraction("5/3")
        assertEquals("5/3", simpleFraction.toString())
    }

    @Test
    fun toStringTest2() {
        val simpleFraction = SimpleFraction("5")
        assertEquals("5/1", simpleFraction.toString())
    }

    @Test
    fun toStringTest3() {
        val simpleFraction = SimpleFraction("-5")
        assertEquals("-5/1", simpleFraction.toString())
    }

    @Test
    fun shortenFractionTest1() {
        val simpleFraction = SimpleFraction("5/10")
        assertEquals("1/2", simpleFraction.toString())
    }

    @Test
    fun shortenFractionTest2() {
        val simpleFraction = SimpleFraction("-4/24")
        assertEquals("-1/6", simpleFraction.toString())
    }

    @Test
    fun shortenFractionTest3() {
        val simpleFraction = SimpleFraction("30/-5")
        assertEquals("-6/1", simpleFraction.toString())
    }

    @Test
    fun shortenFractionTest4() {
        val simpleFraction = SimpleFraction("-12/-3")
        assertEquals("4/1", simpleFraction.toString())
    }

    @Test
    fun toIntTest() {
        val simpleFraction = SimpleFraction("-12/-3")
        assertEquals(4, simpleFraction.toInt())
    }
    @Test
    fun toDoubleTest() {
        val simpleFraction = SimpleFraction("-3/5")
        assertEquals(-0.6, simpleFraction.toDouble())
    }
    @Test
    fun plusTest1(){
        val simpleFractionFirst = SimpleFraction("1/2")
        val simpleFractionSecond = SimpleFraction("1/3")
        assertEquals("5/6", (simpleFractionFirst + simpleFractionSecond).toString())
    }
    @Test
    fun plusTest2(){
        val simpleFractionFirst = SimpleFraction("-1/2")
        val simpleFractionSecond = SimpleFraction("1/3")
        assertEquals("-1/6", (simpleFractionFirst + simpleFractionSecond).toString())
    }
    fun minusTest(){
        val simpleFractionFirst = SimpleFraction("1/2")
        val simpleFractionSecond = SimpleFraction("1/6")
        assertEquals("1/3", (simpleFractionFirst - simpleFractionSecond).toString())
    }
    fun divTest(){
        val simpleFractionFirst = SimpleFraction("1/2")
        val simpleFractionSecond = SimpleFraction("1/6")
        assertEquals("3/1", (simpleFractionFirst / simpleFractionSecond).toString())
    }
    fun timesTest(){
        val simpleFractionFirst = SimpleFraction("4/2")
        val simpleFractionSecond = SimpleFraction("1/6")
        assertEquals("1/3", (simpleFractionFirst * simpleFractionSecond).toString())
    }
    fun unaryMinusTest() {
        val simpleFraction = SimpleFraction("4/2")
        assertEquals("-2/1", (-simpleFraction).toString())
    }
    fun equalsTest1(){
        val simpleFractionFirst = SimpleFraction("4/2")
        val simpleFractionSecond = SimpleFraction("8/4")
        assertEquals(true, simpleFractionFirst == simpleFractionSecond)
    }
}