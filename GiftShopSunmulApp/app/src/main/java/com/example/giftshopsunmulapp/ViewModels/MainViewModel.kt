package com.example.giftshopsunmulapp.ViewModels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
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
import com.example.giftshopsunmulapp.model.user
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    fun getPublicUrl(bucketName: String, filePath: String): String
    {
        // Доступ к бакету через Supabase Storage
        val bucket = Constants.supabase.storage[bucketName]

        // Явное построение публичного URL
        return "https://${Constants.supabase.supabaseUrl}/storage/v1/object/public/$filePath"
    }

    fun getRevCount(countRev: Int?): String
    {
        var text = ""
        if (countRev == 1) { text = "отзыв" }
        else if (countRev in 2..4) { text = "отзыва" }
        else { text = "отзывов" }
        return "${countRev} ${text}"
    }

    @Composable
    fun BtNavnBarP(navHost: NavHostController)
    {
        BottomNavigation(
            modifier = Modifier.fillMaxWidth().height(60.dp),
            backgroundColor = blue,
            contentColor = white,
        ) {
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.package_search_main), contentDescription = null, tint = lightGreen) },
                selected = true,
                onClick = {  navHost.navigate("ProdPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.search), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("SearchPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.shopping_bag), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("OrdersPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.shopping_basket), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("BasketPage")  }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.user), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("HistoryPage")  }
            )
        }
    }

    @Composable
    fun BtNavnBarS(navHost: NavHostController)
    {
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            backgroundColor = blue,
            contentColor = white,
        ) {
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.package_search), contentDescription = null, tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("ProdPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.search_main), contentDescription = null,tint = lightGreen) },
                selected = true,
                onClick = {  navHost.navigate("SearchPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.shopping_bag), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("OrdersPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.shopping_basket), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("BasketPage")  }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.user), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("HistoryPage")  }
            )
        }
    }

    @Composable
    fun BtNavnBarO(navHost: NavHostController)
    {
        BottomNavigation(
            modifier = Modifier.fillMaxWidth().height(60.dp),
            backgroundColor = blue,
            contentColor = white,
        ) {
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.package_search), contentDescription = null, tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("ProdPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.search), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("SearchPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.shopping_bag_main), contentDescription = null,tint = lightGreen) },
                selected = true,
                onClick = {  navHost.navigate("OrdersPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.shopping_basket), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("BasketPage")  }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.user), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("HistoryPage")  }
            )
        }
    }

    @Composable
    fun BtNavnBarB(navHost: NavHostController)
    {
        BottomNavigation(
            modifier = Modifier.fillMaxWidth().height(60.dp),
            backgroundColor = blue,
            contentColor = white,
        ) {
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.package_search), contentDescription = null, tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("ProdPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.search), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("SearchPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.shopping_bag), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("OrdersPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.shopping_basket_main), contentDescription = null,tint = lightGreen) },
                selected = true,
                onClick = {  navHost.navigate("BasketPage")  }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.user), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("HistoryPage")  }
            )
        }
    }

    @Composable
    fun BtNavnBarU(navHost: NavHostController)
    {
        BottomNavigation(
            modifier = Modifier.fillMaxWidth().height(60.dp),
            backgroundColor = blue,
            contentColor = white,
        ) {
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.package_search), contentDescription = null, tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("ProdPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.search), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("SearchPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.shopping_bag), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("OrdersPage") }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.shopping_basket), contentDescription = null,tint = lightBlue) },
                selected = false,
                onClick = {  navHost.navigate("BasketPage")  }
            )
            BottomNavigationItem(
                icon = { Icon(painterResource(R.drawable.user_main), contentDescription = null,tint = lightGreen) },
                selected = true,
                onClick = {  navHost.navigate("HistoryPage")  }
            )
        }
    }

    val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded.asStateFlow()

    val _listCategories = MutableStateFlow<List<categories>>(emptyList())
    var ListCategories: StateFlow<List<categories>> = _listCategories
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
                _listCategories.value = Constants.supabase.from("categories").select().decodeList<categories>().sortedBy { it.title }

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