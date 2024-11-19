package com.example.giftshopsunmulapp.ViewModels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class MainViewModel:ViewModel()
{
    object PrefsHelper
    {
        private lateinit var sharedPreferences: SharedPreferences

        fun init(context: Context)
        {
            sharedPreferences = context.getSharedPreferences("user_email", Context.MODE_PRIVATE)
        }

        fun getSharedPreferences(): SharedPreferences = sharedPreferences
    }
}