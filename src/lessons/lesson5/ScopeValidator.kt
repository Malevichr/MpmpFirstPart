package lessons.lesson5

import myTools.Validator
import java.util.*

class ScopeValidator(private val string: String): Validator{
    companion object {
        val scopes = mutableMapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')
    }
    override fun validate(): Boolean {
        val myStack = Stack<Char>()
        for (i: Char in string){
            if (i in scopes.values){
                myStack.push(i)
            }
            if (i in scopes.keys){
                if (myStack.empty())
                    throw IllegalArgumentException("In [$string] scope no valid")
                if (myStack.peek() == scopes[i])
                    myStack.pop()
            }
        }
        if (!myStack.empty())
            throw IllegalArgumentException("In [$string] scope no valid")
        return true
    }
}
