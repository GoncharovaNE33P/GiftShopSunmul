package com.example.giftshopsunmulapp.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.giftshopsunmulapp.View.Avtorization
import com.example.giftshopsunmulapp.View.MainPage
import com.example.giftshopsunmulapp.View.ProdPage
import com.example.giftshopsunmulapp.View.Registration
import com.example.giftshopsunmulapp.View.SearchPage
import com.example.giftshopsunmulapp.ViewModels.AvtorizationVM
import com.example.giftshopsunmulapp.ViewModels.MainViewModel
import com.example.giftshopsunmulapp.ViewModels.ProdPageVM
import com.example.giftshopsunmulapp.ViewModels.RegistrationVM
import com.example.giftshopsunmulapp.ViewModels.SearchPageVM

@Composable
fun Navigation(viewModel: MainViewModel, context: Context)
{
   /* var sharedPreferences = viewModel.sharedPreferences
    sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val userEmail = viewModel.sharedPreferences!!.getString("user_email", null)


    if (userEmail != null) {
        startPage = "ProdPage"
    } else {
        startPage = "Avtorization"*/

    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "Avtorization")
    {
        composable("MainPage")
        { MainPage(navController)}

        composable("Avtorization")
        { Avtorization(navController, AvtorizationVM())}

        composable("Registration")
        { Registration(navController, RegistrationVM())}

        composable("ProdPage")
        { ProdPage(navController, ProdPageVM())}

        composable("SearchPage")
        { SearchPage(navController, ProdPageVM(),SearchPageVM())}
    }
}