package com.example.giftshopsunmulapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.giftshopsunmulapp.View.Avtorization
import com.example.giftshopsunmulapp.ViewModels.AvtorizationVM
import com.example.giftshopsunmulapp.ViewModels.MainViewModel

@Composable
fun Navigation(viewModel: MainViewModel)
{
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "Avtorization")
    {
        composable("Avtorization")
        { Avtorization(navController, AvtorizationVM())}
    }
}