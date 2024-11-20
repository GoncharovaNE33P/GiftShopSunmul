package com.example.giftshopsunmulapp.ViewModels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.categories
import com.example.giftshopsunmulapp.model.countryProd
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.model.productsStatus
import com.example.giftshopsunmulapp.model.user
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

    val _listProd = MutableStateFlow<List<products>>(emptyList())
    var ListProd: StateFlow<List<products>> = _listProd

    fun loadList()
    {
        viewModelScope.launch {
            try
            {
                val _User = Constants.supabase.from("user").select().decodeList<user>()
                val _Prod = Constants.supabase.from("products").select().decodeList<products>()
                val _Categories = Constants.supabase.from("categories").select().decodeList<categories>()
                val _ProdStatus = Constants.supabase.from("productsStatus").select().decodeList<productsStatus>()
                val _CountyProd = Constants.supabase.from("countryProd").select().decodeList<countryProd>()

                _listProd.value = _Prod.map { prod ->
                    prod.prodStatus = _ProdStatus.find { it.id == prod.prodStatus_id }
                    prod.categories = _Categories.find { it.id == prod.categories_id }
                    prod.country = _CountyProd.find { it.id == prod.country_id }
                    prod
                }

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