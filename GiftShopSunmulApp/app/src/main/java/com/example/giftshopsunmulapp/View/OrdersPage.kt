package com.example.giftshopsunmulapp.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
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
import com.example.giftshopsunmulapp.ViewModels.HistoryPageVM
import com.example.giftshopsunmulapp.ViewModels.MainViewModel
import com.example.giftshopsunmulapp.ViewModels.OrdersPageVM
import com.example.giftshopsunmulapp.model.orders
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white
import kotlinx.coroutines.launch


//@Preview
@Composable
fun OrdersPage(navHost: NavHostController, viewModel: OrdersPageVM)
{
    val orders by viewModel.ListOrders.collectAsState()
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
            MainPageContentOP(navHost,orders,viewModel)
        }
    }
    Box()
    {
        Row(modifier = Modifier.align(Alignment.BottomCenter))
        { viewModel.BtNavnBarO(navHost) }
    }
}

@Composable
fun MainPageContentOP(navHost: NavHostController, orders: List<orders>, viewModel: OrdersPageVM)
{
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = white)
    )
    {
        Text("Заказы",
            fontSize = 40.sp,
            color = blue,
            textAlign = TextAlign.Center,
            lineHeight = 45.sp,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.fillMaxWidth().padding(top = 40.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.background(white)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth().height(30.dp)
                    .background(lightBlue)
                    .padding(horizontal = 15.dp)
            )
            {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 2.dp))
                {
                    Column(
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .size(25.dp)
                    )
                    {
                        IconButton(onClick = {  })
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_down_wide_narrow),
                                contentDescription = "",
                                modifier = Modifier.size(25.dp),
                                tint = blue,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "Статус",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W900,
                        color = blue,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.padding(bottom = 70.dp, start = 20.dp, end = 20.dp).background(white))
        {
            items(orders){ ord ->
                Column(modifier = Modifier.clip(RoundedCornerShape(15.dp)))
                {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth().height(60.dp)
                            .background(lightGreen),
                        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            Text(
                                text = "Заказ ${viewModel.formatDate(ord.date_order)}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = blue
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = ord.article,
                                style = MaterialTheme.typography.bodyLarge,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = blue
                            )
                        }
                    }
                    Row(modifier = Modifier.background(lightBlue)
                        .fillMaxWidth()
                        .height(3.dp)
                    ){}
                    Column(
                        modifier = Modifier
                            .fillMaxWidth().height(220.dp)
                            .background(lightGreen),
                        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Column( modifier = Modifier
                            .fillMaxWidth().padding(horizontal = 25.dp)
                        )
                        {
                            Text(
                                text = viewModel.getStatus(ord.deliv_methods!!.title),
                                style = MaterialTheme.typography.bodyLarge,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = blue
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Статус: ${ord.ord_status!!.title}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = blue
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Дата доставки: ${viewModel.formatDate(ord.date_delivery)}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = blue
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(verticalAlignment = Alignment.CenterVertically)
                            {
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(color = white)
                                        .border(
                                            border = BorderStroke(2.dp, color = blue),
                                            shape = RoundedCornerShape(2.dp)
                                        )
                                )
                                {
                                    val productImage = ord.products.firstOrNull()?.image
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(productImage)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = "",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                val productTitle = ord.products.firstOrNull()?.title
                                Text(
                                    text = productTitle.toString(),
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = blue,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = {},
                                modifier = Modifier.height(35.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = blue,
                                    containerColor = lightBlue
                                )
                            )
                            {
                                Text(
                                    "Оставить отзыв",
                                    fontSize = 15.sp,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = blue,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}





