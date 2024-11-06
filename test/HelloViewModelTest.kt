import helloworld.data.HelloRepository
import helloworld.presentation.HelloViewModel
import helloworld.presentation.UiState
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.jupiter.api.Test

class HelloViewModelTest {
    private lateinit var viewModel: HelloViewModel
    private lateinit var repository: HelloRepository
    @Test
    fun test(){
        repository = HelloRepository.Base()
        viewModel = HelloViewModel(
            repository
        )

        val actual = viewModel.hello()
        val expected = UiState.HelloWorld
        assertEquals(expected, actual)
    }
}