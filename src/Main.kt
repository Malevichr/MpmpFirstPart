import firstLesson.FirstLesson

fun main(){
    val number =  Reader.intInput()
    val runner = FirstLesson(number)
    runner.start()
}
object Reader{
    fun intInput(): Int{
        try {
            val number:Int = readln().toInt()
            return number
        }
        catch (e:Exception){
            println("Not number, enter again")
            return intInput()
        }
    }
}