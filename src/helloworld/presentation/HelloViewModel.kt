package helloworld.presentation

import helloworld.data.LoadResult
import helloworld.data.HelloRepository

class HelloViewModel(
    private val repository: HelloRepository,
) {
    fun hello(): UiState {
        val loadResult: LoadResult = repository.load()
        return if (loadResult.isError())
            UiState.Error("Error, world!")
        else
            UiState.HelloWorld
    }
}
