package com.example.giftshopsunmulapp.model

class ResultStateSignIn {
    sealed class ResultStateSignIn {
        data object Loading : ResultStateSignIn()
        data class Success(val data: Unit) : ResultStateSignIn()
        data class Error(val message: String) : ResultStateSignIn()
    }
}