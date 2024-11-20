package com.example.giftshopsunmulapp.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.products
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

enum class SortOption {
    RatingDescending,
    PriceAscending,
    PriceDescending,
    Popularity
}

class SearchPageVM: MainViewModel(){

    val _foundProd = _listProd
    var FoundProd: StateFlow<List<products>> = _foundProd

    fun filterProd(searchStr: String){
        viewModelScope.launch {
            val allProd = _listProd.value.filter { it.title.contains(searchStr, true) }
            _listProd.value = allProd
        }
    }
    init {
        loadList()
    }

}