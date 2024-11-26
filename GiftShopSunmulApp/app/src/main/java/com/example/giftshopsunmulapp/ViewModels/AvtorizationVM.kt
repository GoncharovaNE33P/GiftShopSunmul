package com.example.giftshopsunmulapp.ViewModels

import androidx.lifecycle.ViewModel
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.ResultStateSignIn.ResultStateSignIn
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class AvtorizationVM: ViewModel(){

    val _signInState = MutableStateFlow<ResultStateSignIn>(ResultStateSignIn.Loading)
    val signInState: StateFlow<ResultStateSignIn> = _signInState.asStateFlow()

    suspend fun Auth(emailUser: String, passwordUser: String): Boolean
    {
        if (emailUser.isBlank() || passwordUser.isBlank()) {
            return false
        }
        return try
        {
            val user = withContext(Dispatchers.IO) {
                Constants.supabase.auth.signInWith(Email) {
                    email = emailUser
                    password = passwordUser
                }
            }
            println(user.toString())
            println(Constants.supabase.auth.currentUserOrNull()!!.id)
            _signInState.value = ResultStateSignIn.Success(user)
            println("Успешно!")
            true
        }
        catch (e: Exception)
        {
            println("Ошибка!")
            println(e.message.toString())
            _signInState.value = ResultStateSignIn.Error(e.message ?: "Unknown error")
            false
        }

    }
}