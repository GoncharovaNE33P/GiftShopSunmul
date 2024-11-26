package com.example.giftshopsunmulapp.ViewModels

import androidx.lifecycle.ViewModel
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.ResultStateSignUp.ResultStateSignUp
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

class RegistrationVM: ViewModel(){

    fun formatDateToSupabase(date: Date?):String
    {
        if(date == null) return "null"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return  dateFormat.format((date))
    }

    val _signUpState = MutableStateFlow<ResultStateSignUp>(ResultStateSignUp.Loading)
    val signUpState: StateFlow<ResultStateSignUp> = _signUpState.asStateFlow()

    suspend fun Reg(
        emailUser: String, passwordUser: String, nameUser:String, phoneUseer:String,
        birthdayUser: String
    ): Boolean
    {
        println("Проверяем текущие значения:")
        println("emailUser: $emailUser")
        println("passwordUser: $passwordUser")
        println("nameUser: $nameUser")
        println("phoneUseer: $phoneUseer")
        println("birthdayUser: $birthdayUser")
        return try
        {
            if (emailUser.isBlank() || passwordUser.isBlank() || nameUser.isBlank() || phoneUseer.isBlank() || birthdayUser.isBlank())
            { return false }

            val user = Constants.supabase.auth.signUpWith(Email) {
                email = emailUser
                password = passwordUser
            }
           val userPublic = Constants.supabase.from("user").insert(
                mapOf(
                    "id" to Constants.supabase.auth.currentUserOrNull()!!.id,
                    "name" to nameUser,
                    "phone" to phoneUseer,
                    "birthday" to birthdayUser,
                    "image" to null
                )
            )
            println(user.toString())
            println(userPublic.toString())
            _signUpState.value = ResultStateSignUp.Success(user)
            println("Успешно!")
            true
        }
        catch (e: Exception)
        {
            println("Ошибка!")
            println(e.message.toString())
            _signUpState.value = ResultStateSignUp.Error(e.message ?: "Unknown error")
            false
        }

    }
}