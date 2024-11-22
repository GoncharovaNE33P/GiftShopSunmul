package com.example.giftshopsunmulapp.View

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.giftshopsunmulapp.model.categories
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.darkBlue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white

@Composable
fun ProdPage(navHost: NavHostController, viewModel: MainViewModel)
{
    val userEmail = MainViewModel.PrefsHelper.getSharedPreferences().getString("user_email", null)
    println("сейчас пользователь " + userEmail)

    val categories by viewModel.ListCategories.collectAsState()
    val products by viewModel.ListProd.collectAsState()

    val isDataLoaded by viewModel.isDataLoaded.collectAsState()

    if (!isDataLoaded)
    {
        Box(
            modifier = Modifier.fillMaxSize().background(white),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = blue)
        }
    } else
    {
        Box()
        {
            MainPageContentPP(
                navHost,
                categories,
                products,
                viewModel)
        }
    }
    Box()
    {
        Row(modifier = Modifier.align(Alignment.BottomCenter))
        { viewModel.BtNavnBarP(navHost) }
    }
}

@Composable
fun MainPageContentPP(navHost: NavHostController,categories: List<categories>, products: List<products>,
 viewModel: MainViewModel = viewModel()
)
{
    val filteredProducts = products.filter { it.rating >= 4.5 }
    val categoryMapping = mapOf(
        "Аксессуары" to "accesories",
        "Декор" to "decor",
        "Книги" to "book",
        "Косметика" to "cosmetics",
        "Кулинария" to "cook",
        "Игры" to "games",
        "Одежда" to "clothes",
        "Спорт" to "sport",
        "Хобби" to "hobby",
        "Гаджеты" to "gadget"
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = white)
    )
    {
        Column( modifier = Modifier
            .background(color = white)
            .padding(top = 50.dp, start = 15.dp)
            .clip(RoundedCornerShape(15.dp)) )
        {
            LazyRow(
                modifier = Modifier
                    .background(color = lightBlue)
                    .height(90.dp)
                    .width(380.dp)
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            )
            {
                items(categories) { category ->
                    val englishName = categoryMapping[category.title]
                    val imagePath = "pictures_categories/$englishName.png"
                    val imageUrl = viewModel.getPublicUrl("pictures_categories", imagePath)
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(92.dp)
                            .padding(start = 5.dp, end = 5.dp))
                    {

                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(color = darkBlue)
                                .border(
                                    border = BorderStroke(1.dp, color = white),
                                    shape = RoundedCornerShape(5.dp)
                                )
                        )
                        {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(imageUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(40.dp)
                                    .align(Alignment.Center)
                                    .clickable {
                                        navHost.navigate("ProdUnderCategory/${category.id}")
                                    }
                            )
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = category.title,
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.bodyLarge,
                            color = blue,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }

        Column( modifier = Modifier
            .background(color = white)
            .padding(top = 10.dp, start = 15.dp)
            .clip(RoundedCornerShape(15.dp)) )
        {
            Column( modifier = Modifier
                .background(color = lightBlue)
                .height(250.dp)
                .width(380.dp)
            )
            {
                Text(
                    text = "Самое популярное",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.W900,
                    color = blue,
                    modifier = Modifier.padding(10.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(25.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                )
                {
                    items(filteredProducts) { prod ->
                        Column(horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .width(100.dp)
                                .padding(start = 5.dp, end = 5.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(color = white)
                                    .border(
                                        border = BorderStroke(1.dp, color = darkBlue),
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
                                    modifier = Modifier.fillMaxSize().clickable {
                                        navHost.navigate("ProdCardPage/${prod.id}")
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "${prod.price} ₽",
                                fontSize = 12.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                color = blue,
                                fontWeight = FontWeight.ExtraBold

                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = prod.title,
                                fontSize = 12.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                color = blue,
                                fontWeight = FontWeight.ExtraBold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Column( modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                            )
                            {
                                Column(
                                    modifier = Modifier
                                        .background(color = lightGreen)
                                        .height(30.dp)
                                        .width(100.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                )
                                {
                                    IconButton(onClick = {  })
                                    {
                                        Icon(
                                            painter = painterResource(id = R.drawable.shopping_basket_prod_page),
                                            contentDescription = "",
                                            modifier = Modifier.size(20.dp),
                                            tint = blue,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

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
                                    modifier = Modifier.fillMaxSize().clickable {
                                        navHost.navigate("ProdCardPage/${prod.id}")
                                    }
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
                                                painter = painterResource(id = R.drawable.shopping_basket_prod_page),
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



