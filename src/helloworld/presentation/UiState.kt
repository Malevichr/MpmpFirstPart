package helloworld.presentation

interface UiState {
    fun show()
    abstract class Abstract(private val message: String) : UiState {
        override fun show() {
            println(message)
        }
    }

    object HelloWorld : Abstract("Hello, world!")
    data class Error(private val message: String) : Abstract(message)
}
