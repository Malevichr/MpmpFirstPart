package lessons.lesson5

import myTools.Validator
import java.util.*

class ScopeValidator(private val string: String): Validator{
    companion object {
        val openScopes  = mutableMapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
        val closeScopes = mutableMapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')
    }
    override fun validate(): Boolean {
        var counter: Int = 0
        val myStack = Stack<Char>()
        for (i: Char in string){
            if (openScopes.containsKey(i)){
                myStack.push(i)
            }
            if (closeScopes.containsKey(i)){
                if (myStack.empty())
                    throw IllegalArgumentException("In [$string] scope no valid")
                if (myStack.peek() == closeScopes[i])
                    myStack.pop()
            }

        }
        if (!myStack.empty())
            throw IllegalArgumentException("In [$string] scope no valid")
        return true
    }
}
