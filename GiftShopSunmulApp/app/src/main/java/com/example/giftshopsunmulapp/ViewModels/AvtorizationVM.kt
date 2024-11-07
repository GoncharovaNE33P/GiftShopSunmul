package com.example.giftshopsunmulapp.ViewModels

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.giftshopsunmulapp.domain.utlis.Constants
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AvtorizationVM: ViewModel(){

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
            println("Успешно!")
            true
        }
        catch (e: Exception)
        {
            println("Ошибка!")
            println(e.message.toString())
            false
        }

    }
}