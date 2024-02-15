package MyTools

object MyReader{
    fun intInput(): Int{
        try {
            val number:Int = readln().toInt()
            return number
        }
        catch (e:Exception){
            println("Not Integer number, enter again")
            return intInput()
        }
    }
    fun doubleInput(): Double{
        try {
            val number = readln().toDouble()
            return number
        }
        catch (e:Exception){
            println("Not Double number, enter again")
            return doubleInput()
        }
    }
    fun stringInput(): String{
        try {
            val str = readln()
            return str
        }
        catch (e:Exception){
            println("Empty String, enter again")
            return stringInput()
        }
    }
}