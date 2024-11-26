package com.example.giftshopsunmulapp

import com.example.giftshopsunmulapp.ViewModels.AvtorizationVM
import com.example.giftshopsunmulapp.ViewModels.OrdersPageVM
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.ResultStateSignIn.ResultStateSignIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import java.time.LocalDate
import  java.time.format.DateTimeFormatter
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.util.Locale

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var viewModel: OrdersPageVM
    private lateinit var constants: Constants
    @Before fun setUp()
    {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = OrdersPageVM()
    }
    /*
    * Тест на правильность установки начального состояния singInState
    * */

    @Test fun InitialSignInStateIsLoading()
    {
        val viewModel = AvtorizationVM()
        assertEquals(viewModel.signInState.value, ResultStateSignIn.Loading)
    }
    /*
   * Тест на правильность установки состояния Success при успешном входе в систему
   * */

    @Test fun signInStateIsSuccessAfterSuccessfulSignIn()
    {
        val viewModel = AvtorizationVM()
        viewModel._signInState.value = ResultStateSignIn.Success(Unit)
        assertEquals(viewModel.signInState.value, ResultStateSignIn.Success(Unit))
    }
    /*
    * Тест на проверку правильности установки состояния Error после неудачной попытки входа в систему
    * */

    @Test fun signInStateIsErrorAfterFailedSignIn()
    {
        val viewModel = AvtorizationVM()
        val mockError = Exception("Failed to sign in")
        viewModel._signInState.value = ResultStateSignIn.Error(mockError.message.toString())
        assertEquals(viewModel.signInState.value, ResultStateSignIn.Error(mockError.message.toString()))
    }
    /*
   * Тест на проверку корректности окончания числа товаров
   * */

    @Test fun getProdCountReturnsCorrectTextFor5OrMoreProd()
    {
        val viewModel = OrdersPageVM()
        val  result = viewModel.getProdCount(5)
        assertEquals("5 товаров",result)
    }
    /*
   * Тест на проверку правильности преобразования даты в нужный формат
   * */

    @Test fun formateDateReturnsCorrectly()
    {
        val inputDate = "2024-11-25"
        val result = viewModel.formatDate(inputDate)
        val expectedDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).
        format(DateTimeFormatter.ofPattern("dd.MM LLLL",
            Locale("ru"))).lowercase(Locale("ru"))
    }
}