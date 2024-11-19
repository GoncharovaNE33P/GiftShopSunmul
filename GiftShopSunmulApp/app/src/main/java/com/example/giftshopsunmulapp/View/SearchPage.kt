package com.example.giftshopsunmulapp.View

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.MainViewModel
import com.example.giftshopsunmulapp.ViewModels.SearchPageVM
import com.example.giftshopsunmulapp.model.categories
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white


//@Preview
@Composable
fun SearchPage(navHost: NavHostController, viewModel: SearchPageVM = viewModel())
{
    val userEmail = MainViewModel.PrefsHelper.getSharedPreferences().getString("user_email", null)
    println("сейчас пользователь " + userEmail)

    val productsFilter by viewModel.FilteredProducts.collectAsState()
    val products by viewModel.ListProd.collectAsState()

    val isDataLoaded by viewModel.isDataLoaded.collectAsState()


    if (!isDataLoaded) {
        Box(
            modifier = Modifier.fillMaxSize().background(white),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = blue)
        }
    } else {
        Box()
        {
//            MainPageContent(
//                navHost,
//                categories,
//                products,
//                viewModel)
            Row(modifier = Modifier.align(Alignment.BottomCenter))
            { BtNavnBarS(navHost) }
        }
    }
}

@Composable
fun BtNavnBarS(navHost: NavHostController)
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
            icon = { Icon(painterResource(R.drawable.search_main), contentDescription = null,tint = lightGreen) },
            selected = true,
            onClick = {  navHost.navigate("SearchPage") }
        )
        BottomNavigationItem(
            icon = { Icon(painterResource(R.drawable.shopping_bag), contentDescription = null,tint = lightBlue) },
            selected = false,
            onClick = {  /**/ }
        )
        BottomNavigationItem(
            icon = { Icon(painterResource(R.drawable.shopping_basket3), contentDescription = null,tint = lightBlue) },
            selected = false,
            onClick = {  /**/  }
        )
        BottomNavigationItem(
            icon = { Icon(painterResource(R.drawable.user), contentDescription = null,tint = lightBlue) },
            selected = false,
            onClick = {  /**/  }
        )
    }
}

@Composable
fun MainPageContent(navHost: NavHostController, categories: List<categories>, products: List<products>,
 viewModel: SearchPageVM = viewModel())
{


    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = white)
    )
    {
        SearchAndSortBar(
            onSearch = { query -> viewModel.filterProducts(query) },
            onSort = { sortOption -> viewModel.sortProducts(sortOption) }
        )
        Column( modifier = Modifier
            .background(color = white)
            .padding(top = 10.dp, start = 15.dp)
            .clip(RoundedCornerShape(15.dp)) )
        {
            Column( modifier = Modifier
                .padding(bottom = 60.dp)
                .width(380.dp) )
            {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.padding(8.dp)
                )
                {
                    items(products) { prod ->
                        Column(horizontalAlignment = Alignment.Start,
                            modifier = Modifier.width(180.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(180.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(color = white)
                                    .border(
                                        border = BorderStroke(2.dp, color = lightGreen),
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            )
                            {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(prod.image)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(180.dp))
                            {
                                Text(
                                    text = "${prod.price} ₽",
                                    fontSize = 20.sp,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = blue,
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier.weight(1f)
                                )

                                Column( modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp)),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                )
                                {
                                    Column(
                                        modifier = Modifier
                                            .background(color = blue)
                                            .size(24.dp)
                                    )
                                    {
                                        IconButton(onClick = {  })
                                        {
                                            Icon(
                                                painter = painterResource(id = R.drawable.shopping_basket1),
                                                contentDescription = "",
                                                modifier = Modifier.size(20.dp),
                                                tint = lightGreen,
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = prod.title,
                                fontSize = 15.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                color = blue,
                                fontWeight = FontWeight.ExtraBold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically)
                            {
                                Icon(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = "",
                                    modifier = Modifier.size(18.dp),
                                    tint = blue,
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "${prod.rating} – ${viewModel.getRevCount(prod.countRev)}",
                                    fontSize = 15.sp,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = blue,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class SortOption {
    RatingDescending,
    PriceAscending,
    PriceDescending,
    Popularity
}

@Composable
fun SearchAndSortBar(
    onSearch: (String) -> Unit,
    onSort: (SortOption) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedSort by remember { mutableStateOf(SortOption.Popularity) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = lightBlue)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Поисковая строка
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = blue,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        onSearch(it) // Вызываем обработчик поиска
                    },
                    placeholder = { Text("Поиск", color = blue) },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = blue
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Сортировка
            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_main), // Ваша иконка сортировки
                        contentDescription = "Sort Icon",
                        tint = blue
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(onClick = {
                        selectedSort = SortOption.RatingDescending
                        onSort(selectedSort)
                        expanded = false
                    }) {
                        Text("По рейтингу (убыв.)")
                    }
                    DropdownMenuItem(onClick = {
                        selectedSort = SortOption.PriceAscending
                        onSort(selectedSort)
                        expanded = false
                    }) {
                        Text("По цене (возраст.)")
                    }
                    DropdownMenuItem(onClick = {
                        selectedSort = SortOption.PriceDescending
                        onSort(selectedSort)
                        expanded = false
                    }) {
                        Text("По цене (убыв.)")
                    }
                    DropdownMenuItem(onClick = {
                        selectedSort = SortOption.Popularity
                        onSort(selectedSort)
                        expanded = false
                    }) {
                        Text("Популярные (отзыв.)")
                    }
                }
            }
        }
    }
}



