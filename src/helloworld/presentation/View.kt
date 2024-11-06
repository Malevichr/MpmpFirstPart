package helloworld.presentation

class View (
    private val viewModel: HelloViewModel
){
    fun print(){
        val uiState: UiState = viewModel.hello()
        uiState.show()
    }
}