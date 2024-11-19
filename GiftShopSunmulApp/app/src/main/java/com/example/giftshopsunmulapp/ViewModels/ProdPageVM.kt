package com.example.giftshopsunmulapp.ViewModels

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.categories
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.model.reviews
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.rpc
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProdPageVM: ViewModel(){

    val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    fun getPublicUrl(bucketName: String, filePath: String): String
    {
        // Доступ к бакету через Supabase Storage
        val bucket = Constants.supabase.storage[bucketName]

        // Явное построение публичного URL
        return "https://${Constants.supabase.supabaseUrl}/storage/v1/object/public/$filePath"
    }

    fun getRevCount(countRev: Int): String
    {
        var text = ""
        if (countRev == 1) { text = "отзыв" }
        else if (countRev in 2..4) { text = "отзыва" }
        else { text = "отзывов" }
        return "${countRev} ${text}"
    }

    private val _listCategories = MutableStateFlow<List<categories>>(emptyList())
    var ListCategories: StateFlow<List<categories>> = _listCategories
    private val _listProd = MutableStateFlow<List<products>>(emptyList())
    var ListProd: StateFlow<List<products>> = _listProd

    private fun loadList()
    {
        viewModelScope.launch {
            try
            {
                _listCategories.value = Constants.supabase.from("categories").select().decodeList<categories>().sortedBy { it.title }
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