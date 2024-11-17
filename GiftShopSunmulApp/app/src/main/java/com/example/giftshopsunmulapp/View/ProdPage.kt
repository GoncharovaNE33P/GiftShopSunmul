package com.example.giftshopsunmulapp.View

import android.R.attr.maxLines
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.giftshopsunmulapp.ViewModels.ProdPageVM
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.darkBlue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white
import io.github.jan.supabase.storage.BucketApi
import io.github.jan.supabase.storage.storage


//@Preview
@Composable
fun ProdPage(navHost: NavHostController, viewModel: ProdPageVM = viewModel())
{
    val categories by viewModel.ListCategories.collectAsState()
    val products by viewModel.ListProd.collectAsState()
    val reviews by viewModel.ListRew.collectAsState()
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
                        modifier = Modifier.width(92.dp).padding(start = 5.dp, end = 5.dp))
                    {

                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(color = darkBlue)
                                .border(border = BorderStroke(1.dp, color = white), shape = RoundedCornerShape(5.dp))
                        )
                        {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(imageUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(40.dp).align(Alignment.Center)
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
            .padding(top = 20.dp, start = 15.dp)
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
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                )
                {
                    items(filteredProducts) { prod ->
                        Column(horizontalAlignment = Alignment.Start,
                            modifier = Modifier.width(100.dp).padding(start = 5.dp, end = 5.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(color = white)
                                    .border(border = BorderStroke(1.dp, color = darkBlue), shape = RoundedCornerShape(5.dp))
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
                            Button(
                                onClick = { /*navHost.navigate("Avtorization")*/ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = lightGreen,
                                    contentColor = blue
                                ),
                                modifier = Modifier.height(30.dp).width(100.dp),
                                shape = RoundedCornerShape(5.dp),
                            )
                            {
                                Column (
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                )
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.shopping_basket),
                                        contentDescription = "",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow()
            {
                items(products) { prod ->
                    Text(
                        text = "${prod.price} ₽",
                        fontSize = 20.sp,
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painterResource(R.drawable.star), contentDescription = null, tint = Color.Yellow)
                        Text(text = "${prod.rating} - 2 отзывов", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}