package com.example.giftshopsunmulapp.ViewModels

import android.annotation.SuppressLint
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
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProdPageVM: ViewModel(){

    val _listCategories = MutableStateFlow<List<categories>>(emptyList())
    var listCategories: StateFlow<List<categories>> = _listCategories

    private val _isLoadData = MutableStateFlow(false)
    val isLoadData: StateFlow<Boolean> = _isLoadData

    fun loadCategories()
    {
        viewModelScope.launch {
            try {
                val categorie = Constants.supabase.from("categories").select().decodeList<categories>()

            }
            catch(e: Exception)
            {

            }
        }
    }
}