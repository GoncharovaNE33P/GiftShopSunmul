package com.example.giftshopsunmulapp.View

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.MainViewModel
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white


//@Preview
@Composable
fun OrderRegistPage(navHost: NavHostController, viewModel: MainViewModel)
{
    val isDataLoaded by viewModel.isDataLoaded.collectAsState()

    if (!isDataLoaded)
    {
        Box(
            modifier = Modifier.fillMaxSize().background(white),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = blue)
        }
    }
    else
    {
        Box()
        {
            MainPageContentORP()
        }
    }
    Box()
    {
        Row(modifier = Modifier.align(Alignment.BottomCenter))
        { viewModel.BtNavnBarO(navHost) }
    }
}

@Composable
fun MainPageContentORP()
{

}





