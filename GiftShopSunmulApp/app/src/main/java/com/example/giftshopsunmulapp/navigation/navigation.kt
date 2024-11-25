package com.example.giftshopsunmulapp.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.giftshopsunmulapp.View.Avtorization
import com.example.giftshopsunmulapp.View.BasketPage
import com.example.giftshopsunmulapp.View.HistoryPage
import com.example.giftshopsunmulapp.View.MainPage
import com.example.giftshopsunmulapp.View.OrderRegistPage
import com.example.giftshopsunmulapp.View.OrdersPage
import com.example.giftshopsunmulapp.View.ProdCardPage
import com.example.giftshopsunmulapp.View.ProdPage
import com.example.giftshopsunmulapp.View.ProdUnderCategory
import com.example.giftshopsunmulapp.View.Registration
import com.example.giftshopsunmulapp.View.ReviewPage
import com.example.giftshopsunmulapp.View.SearchPage
import com.example.giftshopsunmulapp.View.UserPage
import com.example.giftshopsunmulapp.ViewModels.AvtorizationVM
import com.example.giftshopsunmulapp.ViewModels.BasketPageVM
import com.example.giftshopsunmulapp.ViewModels.HistoryPageVM
import com.example.giftshopsunmulapp.ViewModels.MainViewModel
import com.example.giftshopsunmulapp.ViewModels.OrderRegistPageVM
import com.example.giftshopsunmulapp.ViewModels.OrdersPageVM
import com.example.giftshopsunmulapp.ViewModels.RegistrationVM
import com.example.giftshopsunmulapp.ViewModels.ReviewPageVM
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
        { ProdPage(navController, MainViewModel())}

        composable("SearchPage")
        { SearchPage(navController, SearchPageVM())}

        composable("OrdersPage")
        { OrdersPage(navController, OrdersPageVM())}

        composable("OrderRegistPage")
        { OrderRegistPage(navController, OrderRegistPageVM())}

        composable("BasketPage")
        { BasketPage(navController, BasketPageVM())}

        composable("UserPage")
        { UserPage(navController, MainViewModel())}

        composable("HistoryPage")
        { HistoryPage(navController, HistoryPageVM()) }

        composable(
            "ProdCardPage/{prod.id}",
            arguments = listOf(
                navArgument(name = "prod.id"){
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            ProdCardPage(navController, MainViewModel(), backStackEntry.arguments?.getString("prod.id"))
        }
        composable(
            "ReviewPage/{prod.id}",
            arguments = listOf(
                navArgument(name = "prod.id"){
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            ReviewPage(navController, ReviewPageVM(), backStackEntry.arguments?.getString("prod.id"))
        }

        composable(
            "ProdUnderCategory/{categ.id}",
            arguments = listOf(
                navArgument(name = "categ.id"){
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            ProdUnderCategory(navController, MainViewModel(), backStackEntry.arguments?.getString("categ.id"))
        }
    }
}