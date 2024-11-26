package com.example.giftshopsunmulapp.model

import io.github.jan.supabase.auth.user.UserInfo

class ResultStateSignUp {
    sealed class ResultStateSignUp {
        data object Loading : ResultStateSignUp()
        data class Success(val data: UserInfo?) : ResultStateSignUp()
        data class Error(val message: String) : ResultStateSignUp()
    }
}