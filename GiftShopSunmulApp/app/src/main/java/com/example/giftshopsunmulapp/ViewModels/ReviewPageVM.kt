package com.example.giftshopsunmulapp.ViewModels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.categories
import com.example.giftshopsunmulapp.model.countryProd
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.model.productsStatus
import com.example.giftshopsunmulapp.model.reviews
import com.example.giftshopsunmulapp.model.user
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class ReviewPageVM:MainViewModel()
{
    fun getReviewsForProduct(reviewsList: List<reviews>, productId: String): List<reviews> {
        return reviewsList.filter { it.products_id == productId }
    }
    val _listRev = MutableStateFlow<List<reviews>>(emptyList())
    var ListRev: StateFlow<List<reviews>> = _listRev

    fun loadRev()
    {
        viewModelScope.launch {
            try
            {
                _listRev.value = Constants.supabase.from("reviews").select().decodeList<reviews>()

                _isDataLoaded.value = true
            }
            catch(e: Exception)
            {
                Log.e("ReviewPageVM", "Error fetching data: ${e.localizedMessage}")
            }
        }
    }
    init
    {
        loadRev()
    }
}