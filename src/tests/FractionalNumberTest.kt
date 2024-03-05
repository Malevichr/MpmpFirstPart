package tests

import lessons.lesson04.FractionalNumber
import org.testng.Assert.assertThrows
import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test
import java.lang.Exception
@Test
class FractionalNumberTest {
    @Test
    fun initWithExceptionTest1(){
        assertThrows(IllegalArgumentException::class.java){
            FractionalNumber("1234УВЫА")
        }
    }
    @Test
    fun initWithExceptionTest2(){
        assertThrows(IllegalArgumentException::class.java){
            FractionalNumber("1234/")
        }
    }
    @Test
    fun initWithExceptionTest3(){
        assertThrows(IllegalArgumentException::class.java){
            FractionalNumber("/1234")
        }
    }
    @Test
    fun initWithExceptionTest4(){
        assertThrows(IllegalArgumentException::class.java){
            FractionalNumber("1234/1234/")
        }
    }

    @Test
    fun toStringTest1(){
        val fractionalNumber = FractionalNumber("5/3")
        assertEquals("5/3", fractionalNumber.toString(), )
    }
    @Test
    fun toStringTest2(){
        val fractionalNumber = FractionalNumber("5")
        assertEquals("5/1", fractionalNumber.toString())
    }


    @Test
    fun toIntTest(){
        TODO("Not yet implemented")
    }
}