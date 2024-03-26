package lessons.lesson5

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.testng.annotations.Test

class FourScopeValidatorTest{
    @Test
    fun test1(){
        val string = "()"
        val scopeValidator = ScopeValidator(string)
        assertEquals(true, scopeValidator.validate())
    }
    @Test
    fun test2(){
        assertThrows<IllegalArgumentException> {
            val string = ")("
            val scopeValidator = ScopeValidator(string)
            assertEquals(true, scopeValidator.validate())
        }
    }
    @Test
    fun test3() {
        assertThrows<IllegalArgumentException> {
            val string = "({}(})"
            val scopeValidator = ScopeValidator(string)
            assertEquals(true, scopeValidator.validate())
        }
    }
}