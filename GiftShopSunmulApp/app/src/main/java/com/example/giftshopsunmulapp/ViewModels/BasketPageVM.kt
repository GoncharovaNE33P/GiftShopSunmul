package com.example.giftshopsunmulapp.ViewModels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.shopCart
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BasketPageVM: MainViewModel()
{
    /*private val _listShortCart = MutableStateFlow<List<shopCart>>(emptyList())
    var ListShortCart: StateFlow<List<shopCart>> = _listShortCart

    private fun loadShortCart()
    {
        viewModelScope.launch {
            try
            {
                _listShortCart.value = Constants.supabase.from("shorCart").select().decodeList<shopCart>()
            }
            catch (e:Exception)
            {
                Log.e("BasketPageVM", "Error fetching data: ${e.localizedMessage}")
            }
        }
    }
    init
    {
        loadShortCart()
    }*/
}