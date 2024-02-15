import MyTools.MyReader
import lessons.firstLesson.FirstLesson

fun main(){
    println("Enter task")
    val number =  MyReader.intInput()
    FirstLesson(number).start()
}