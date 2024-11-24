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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.giftshopsunmulapp.model.orders
import com.example.giftshopsunmulapp.model.products
import com.example.giftshopsunmulapp.ui.theme.blue
import com.example.giftshopsunmulapp.ui.theme.lightBlue
import com.example.giftshopsunmulapp.ui.theme.lightGreen
import com.example.giftshopsunmulapp.ui.theme.white
import com.example.giftshopsunmulapp.domain.utlis.Constants
import com.example.giftshopsunmulapp.model.prodInOrder
import com.example.giftshopsunmulapp.model.user
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from


//@Preview
@Composable
fun HistoryPage(navHost: NavHostController, viewModel: HistoryPageVM)
{
    val products by viewModel.ListProd.collectAsState()
    val history by viewModel.ListHistory.collectAsState()
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
            MainPageContentHP(navHost,products,history,viewModel)
        }
    }
    Box()
    {
        Row(modifier = Modifier.align(Alignment.BottomCenter))
        { viewModel.BtNavnBarU(navHost) }
    }
}

@Composable
fun MainPageContentHP(navHost: NavHostController,products: List<products>,history: List<orders>,viewModel: HistoryPageVM)
{
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = white)
    )
    {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.background(white)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth().height(110.dp)
                    .background(blue)
                    .padding(horizontal = 15.dp)
            )
            {
                Spacer(modifier = Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Column(
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .size(30.dp)
                    )
                    {
                        IconButton(onClick = { navHost.navigate("UserPage") })
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.backprodpage),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp),
                                tint = lightBlue,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "Купленные товары",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W900,
                        color = lightBlue
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 5.dp))
                {
                    Text(
                        text = viewModel.getProdCount(history.count()),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W900,
                        color = lightGreen
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Column(
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .size(30.dp)
                    )
                    {
                        IconButton(onClick = {  })
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_down_wide_narrow),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp),
                                tint = lightBlue,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "Отсортировать",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W900,
                        color = lightBlue
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.background(white)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth().height(35.dp)
                    .background(lightBlue)
                    .padding(horizontal = 15.dp)
            )
            {
                Spacer(modifier = Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { navHost.navigate("OrdersPage") })
                {
                    Text(
                        text = "Заказы",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W900,
                        color = blue
                    )
                    Spacer(modifier = Modifier.width(230.dp))
                    Box()
                    {
                        Icon(
                            painterResource(R.drawable.shopping_bag_main),
                            contentDescription = null,
                            tint = blue,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
        }

        if (history.isEmpty())
        {
            Spacer(modifier = Modifier.height(30.dp))
            Column(modifier = Modifier.align(Alignment.CenterHorizontally))
            {
                Text(
                    text = "История покупок\n" +
                            "отсутствует",
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.W900,
                    color = blue,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { navHost.navigate("ProdPage")},
                modifier = Modifier.width(280.dp).height(50.dp).align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = blue,
                    containerColor = lightGreen
                )
            )
            {
                Text(
                    "Приступить к покупкам",
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.W900,
                    color = blue
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Рекомендованные товары",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                fontWeight = FontWeight.W900,
                color = blue,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))
            Column( modifier = Modifier
                .background(color = white)
                .padding(start = 15.dp)
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
        else
        {
            Spacer(modifier = Modifier.height(5.dp))
            Column( modifier = Modifier
                .background(color = white)
                .padding(start = 15.dp)
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
                        items(history) { hisprod ->
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
                                    val productImage = hisprod.products.firstOrNull()?.image
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(productImage)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = "",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.fillMaxSize().clickable {
                                            val productId = hisprod.products.firstOrNull()?.id
                                            navHost.navigate("ProdCardPage/${productId}")
                                        }
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(180.dp))
                                {
                                    val productPrice = hisprod.products.firstOrNull()?.price
                                    Text(
                                        text = "${productPrice} ₽",
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
                                val productTitle = hisprod.products.firstOrNull()?.title
                                Text(
                                    text = productTitle.toString(),
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
                                    val productRating = hisprod.products.firstOrNull()?.rating
                                    val productCountRev = hisprod.products.firstOrNull()?.countRev
                                    hisprod.products.map { it.rating }.let {
                                        Text(
                                            text = "$productRating – ${viewModel.getRevCount(productCountRev)}",
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
    }
}