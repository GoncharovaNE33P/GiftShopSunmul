package com.example.giftshopsunmulapp.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Text
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.giftshopsunmulapp.R
import com.example.giftshopsunmulapp.ViewModels.MainViewModel
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white

@Composable
fun ProdCardPage(navHost: NavHostController, viewModel: MainViewModel, prodId:String?)
{
    val listProd by viewModel.ListProd.collectAsState()
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
            MainPageContentPCP(navHost,prodId,listProd)
        }
    }
    Box()
    {
        Row(modifier = Modifier.align(Alignment.BottomCenter))
        { BtNavnBarC(navHost) }
    }
}

@Composable
fun MainPageContentPCP(navHost: NavHostController, prodId:String?,listProd:List<products>)
{
    val prod = listProd.find { it.id == prodId }!!

    Column(modifier = Modifier
        .background(color = white)
        .fillMaxSize()
    )
    {
        Column( modifier = Modifier.padding(start = 20.dp, bottom = 10.dp, top = 30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        )
        {
            Column(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .size(30.dp)
            )
            {
                IconButton(onClick = { navHost.navigate("ProdPage") })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.backprodpage),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp),
                        tint = blue,
                    )
                }
            }
        }
        LazyColumn(modifier = Modifier.padding(bottom = 110.dp))
        {
            item {
                Column(modifier = Modifier
                    .padding(start = 20.dp, bottom = 10.dp, end = 20.dp)
                    .background(white)
                    .clip(RoundedCornerShape(10.dp))
                )
                {
                    Column( modifier = Modifier
                        .background(blue).fillMaxWidth().height(350.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    )
                    {
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(white)
                                .height(200.dp),
                            contentAlignment = Alignment.Center
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
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically)
                            {
                                Text(
                                    text = "${prod.price} ₽",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = lightGreen,
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                                Spacer(modifier = Modifier.width(160.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.star),
                                        contentDescription = "Рейтинг",
                                        tint = lightGreen,
                                        modifier = Modifier.size(30.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "${prod.rating}",
                                        fontSize = 25.sp,
                                        color = lightGreen,
                                        fontWeight = FontWeight.ExtraBold,
                                        style = MaterialTheme.typography.bodyLarge,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = prod.title,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = lightGreen,
                                style = MaterialTheme.typography.bodyLarge,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.background(white)
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth().height(85.dp)
                            .background(lightGreen)
                            .padding(horizontal = 15.dp)
                    )
                    {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically)
                        {
                            Text(
                                text = prod.categories!!.title,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = blue,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = prod.prodStatus!!.title,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = blue,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Отзывы: ${prod.countRev}",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(lightBlue)
                                    .padding(5.dp)
                                    .clickable { navHost.navigate("ReviewPage/${prod.id}") },
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = blue,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .clip(RoundedCornerShape(5.dp)),
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            Button(
                                onClick = { },
                                modifier = Modifier.fillMaxWidth().height(30.dp),
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = lightBlue,

                                    )
                            )
                            {
                                Text(
                                    "Купить",
                                    fontSize = 12.sp,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = blue
                                )
                            }
                        }
                    }
                }
            }
            item{
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .border(BorderStroke(2.dp, lightBlue), shape = RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp)

                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Характеристики",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W900,
                            color = blue
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        val characteristics = listOf(
                            "Артикул" to prod.article.toString(),
                            "Вес товара, гр." to prod.grams.toString(),
                            "Страна" to (prod.country?.title ?: "Не указано"),
                            "Признак 18+" to if (prod.sing_18) "да" else "нет",
                            "Описание" to (prod.description ?: "...")
                        )
                        characteristics.forEach{ (key, value) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            )
                            {
                                Text(
                                    text = key,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = blue,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = value,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = blue,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BtNavnBarC(navHost: NavHostController)
{
    Column()
    {
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = lightGreen
            )
        ) {
            Text(
                text = "В корзину",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                fontWeight = FontWeight.W900,
                color = blue
            )
        }
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
                onClick = {  navHost.navigate("UserPage")  }
            )
        }
    }
}





