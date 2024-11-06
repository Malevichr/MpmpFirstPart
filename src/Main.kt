import helloworld.data.HelloRepository
import helloworld.presentation.HelloViewModel
import helloworld.presentation.View
import lessons.lesson02.Lesson2
import lessons.lesson02.RomanNum
import lessons.lesson04.SimpleFraction

fun main() {
    val view = View(
        HelloViewModel(
            HelloRepository.Base()
        )
    )
    view.print()
}