package tests

import lessons.lesson02.toRoman
import org.testng.AssertJUnit.assertEquals
import org.testng.annotations.Test


class RomanTest {
    @Test
    fun intToRomanTest(){
        for(i in 1..3999){
            assertEquals(i, i.toRoman().toInt())
        }
    }
    @Test
    fun doubleToRomanTest(){
        for(i in 1..3999){
            assertEquals(i.toDouble(), i.toRoman().toDouble())
        }
    }
    @Test
    fun romanPlusTest(){
        for (i in 1..1000){
            assertEquals((i + 2*i).toRoman().getValue(), (i.toRoman() + 2*i).getValue())
        }
    }
    @Test
    fun romanMinusTest(){
        for (i in 1..1000){
            assertEquals((3*i - i).toRoman().getValue(), ((3*i).toRoman() - i).getValue())
        }
    }
    @Test
    fun equalsTest(){
        for (i in 1..3999){
            assertEquals(i.toRoman() == i.toRoman(), true)
        }
    }


}