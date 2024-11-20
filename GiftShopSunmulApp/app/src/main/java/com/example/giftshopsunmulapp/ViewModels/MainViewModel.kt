package com.example.giftshopsunmulapp.ViewModels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.categories
import com.example.giftshopsunmulapp.model.products
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    val _listCategories = MutableStateFlow<List<categories>>(emptyList())
    var ListCategories: StateFlow<List<categories>> = _listCategories
    val _listProd = MutableStateFlow<List<products>>(emptyList())
    var ListProd: StateFlow<List<products>> = _listProd

    fun loadList()
    {
        viewModelScope.launch {
            try
            {
                _listCategories.value = Constants.supabase.from("categories").select().decodeList<categories>()
                _listProd.value = Constants.supabase.from("products").select().decodeList<products>()
                _isDataLoaded.value = true
            }
            catch(e: Exception)
            {
                Log.e("MainPageViewModel", "Error fetching data: ${e.localizedMessage}")
            }
        }
    }
    init
    {
        loadList()
    }
}