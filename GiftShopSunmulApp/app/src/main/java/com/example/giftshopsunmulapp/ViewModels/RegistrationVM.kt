package com.example.giftshopsunmulapp.ViewModels

import androidx.lifecycle.ViewModel
import com.example.giftshopsunmulapp.domain.utlis.Constants
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

class RegistrationVM: ViewModel(){

    suspend fun Reg(
        emailUser: String, passwordUser: String, nameUser:String, phoneUseer:String,
        birthdayUser: Date?
    ): Boolean
    {
        if (emailUser.isBlank() || passwordUser.isBlank() || nameUser.isBlank() || phoneUseer.isBlank() || birthdayUser == null) {
            return false
        }
        println("Проверяем текущие значения:")
        println("emailUser: $emailUser")
        println("passwordUser: $passwordUser")
        println("nameUser: $nameUser")
        println("phoneUseer: $phoneUseer")
        println("birthdayUser: $birthdayUser")
        return try
        {
            val user = withContext(Dispatchers.IO) {
                Constants.supabase.auth.signInWith(Email) {
                    email = emailUser
                    password = passwordUser
                }
            }
            val currentUser = Constants.supabase.auth.currentUserOrNull()
            if (currentUser == null) {
                println("Ошибка: пользователь не авторизован")
                return false
            }
            val userPublic = Constants.supabase.from("user").insert(
                mapOf(
                    "id" to currentUser.id,
                    "name" to nameUser,
                    "phone" to phoneUseer,
                    "birthday" to birthdayUser,
                    "image" to null
                )
            )
            println(user.toString())
            println(userPublic.toString())
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