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

class SearchPageVM: ViewModel(){

    val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    fun getRevCount(countRev: Int): String
    {
        var text = ""
        if (countRev == 1) { text = "отзыв" }
        else if (countRev in 2..4) { text = "отзыва" }
        else { text = "отзывов" }
        return "${countRev} ${text}"
    }

    private val _listProd = MutableStateFlow<List<products>>(emptyList())
    private val _filteredProducts = MutableStateFlow<List<products>>(emptyList())

    var ListProd: StateFlow<List<products>> = _listProd
    var FilteredProducts: StateFlow<List<products>> = _filteredProducts

    fun filterProducts(query: String) {
        _filteredProducts.value = _listProd.value.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    fun sortProducts(sortOption: com.example.giftshopsunmulapp.View.SortOption) {
        /*_filteredProducts.value = when (sortOption) {
            SortOption.RatingDescending -> _filteredProducts.value.sortedByDescending { it.rating }
            SortOption.PriceAscending -> _filteredProducts.value.sortedBy { it.price }
            SortOption.PriceDescending -> _filteredProducts.value.sortedByDescending { it.price }
            SortOption.Popularity -> _filteredProducts.value.sortedByDescending { it.countRev }
        }*/
    }

    private fun loadList()
    {
        viewModelScope.launch {
            try
            {
                _listProd.value = Constants.supabase.from("products").select().decodeList<products>().sortedBy { it.title }
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